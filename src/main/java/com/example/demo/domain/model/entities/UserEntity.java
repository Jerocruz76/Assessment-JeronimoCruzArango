package com.example.demo.domain.model.entities;

import com.example.demo.domain.model.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "speciality")
    private String speciality;

    @OneToMany(mappedBy = "doctor")
    private List<Availability> availabilityList;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;
}
