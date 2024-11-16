package com.example.demo.domain.repositories;

import com.example.demo.domain.model.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorName(String doctorName);
    List<Appointment> findByPatientName(String patientName);
    Optional<Appointment> findByDate(LocalDate date);
    Optional<Appointment> findByReason(String reason);
}
