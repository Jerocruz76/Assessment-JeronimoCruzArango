package com.example.demo.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private UserEntity doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private UserEntity patientId;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "start", nullable = false)
    private LocalTime appointmentStart;

    @Column(name = "end", nullable = false)
    private LocalTime appointmentEnd;
}
