package com.example.demo.web;


import com.example.demo.application.dto.request.LoginRequest;
import com.example.demo.application.dto.request.RegisterRequest;
import com.example.demo.application.dto.response.LoginResponseDataDto;
import com.example.demo.application.dto.response.LoginResponseDto;
import com.example.demo.application.dto.response.RegisterResponseDto;
import com.example.demo.application.services.impl.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @Valid
            @RequestBody(required = true)
            RegisterRequest request) {
        authService.register(request);
        RegisterResponseDto responseDto = RegisterResponseDto.builder()
                .status(HttpStatus.CREATED.value())
                .message("Successfully registered")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Log in a user", description = "Authenticates a user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid
            @RequestBody(required = true)
            LoginRequest request) {
        String token = authService.login(request);
        LoginResponseDataDto loginResponseDataDto = new LoginResponseDataDto(token);
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .status(HttpStatus.OK.value())
                .message("Successfully logged in")
                .data(loginResponseDataDto)
                .build();
        return ResponseEntity.ok(responseDto);
    }
}
