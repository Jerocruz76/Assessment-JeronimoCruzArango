package com.example.demo.application.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String reason;
    private String description;
    private LocalDate date;
    private LocalTime appointmentStart;
    private LocalTime appointmentEnd;
}
