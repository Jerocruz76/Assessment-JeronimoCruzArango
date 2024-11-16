package com.example.demo.application.dto.request;

import com.example.demo.domain.model.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {

    @NotBlank(message = "doctor is mandatory")
    private UserEntity doctorId;

    @NotBlank(message = "patient is mandatory")
    private UserEntity patientId;

    @NotBlank(message = "reason is mandatory")
    private String reason;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotBlank(message = "date is mandatory")
    private LocalDate date;

    @NotBlank(message = "start is mandatory")
    private LocalTime appointmentStart;

    @NotBlank(message = "end is mandatory")
    private LocalTime appointmentEnd;
}
