package com.example.demo.domain.repositories;

import com.example.demo.domain.model.entities.UserEntity;
import com.example.demo.domain.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByRole(Roles roles);
    public Optional<UserEntity> findByEmail(String email);
}
