package com.example.demo.application.services.impl;
import com.example.demo.application.dto.request.LoginRequest;
import com.example.demo.application.dto.request.RegisterRequest;
import com.example.demo.domain.model.entities.UserEntity;
import com.example.demo.domain.model.enums.Roles;
import com.example.demo.domain.repositories.UserRepository;
import com.example.demo.infrastructure.security.JwtUtils;
import com.example.demo.infrastructure.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authManager;

    public void register(RegisterRequest request){
        Optional<UserEntity> foundUser = userRepository.findByEmail(request.getEmail());
        if(foundUser.isPresent()){
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Email already registered");
        }
        String encodedPassword = this.passwordEncoder.encode(request.getPassword());
        Roles roleToRegister = Roles.valueOf(request.getRole());
        var userEntity = UserEntity.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(roleToRegister)
                .build();

        var savedUser = userRepository.save(userEntity);
        String message = "User with email " + savedUser.getEmail() + " registered successfully";
        logger.info(message);
    }

    public String login(LoginRequest request){
        UserEntity userExists = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found with email: " + request.getEmail())
        );
        this.authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = new UserDetailsImpl(userExists);
        String token = jwtUtils.generateToken(Map.of("role", userExists.getRole()), userDetails);
        return token;
    }
}
