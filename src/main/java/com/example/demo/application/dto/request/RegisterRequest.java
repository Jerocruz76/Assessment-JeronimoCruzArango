package com.example.demo.application.dto.request;

import com.example.demo.domain.model.enums.Roles;
import com.example.demo.infrastructure.validators.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "identification is mandatory")
    private String identification;

    @NotBlank(message = "full name is mandatory")
    private String fullName;

    @NotBlank(message = "age is mandatory")
    private Integer age;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @Schema(example = "PATIENT", description = "patient's role", implementation = Roles.class)
    @NotBlank(message = "role is mandatory")
    @ValidEnum(enumClass = Roles.class, message = "role must be PATIENT, ADMIN or DOCTOR")
    private String role;
}
