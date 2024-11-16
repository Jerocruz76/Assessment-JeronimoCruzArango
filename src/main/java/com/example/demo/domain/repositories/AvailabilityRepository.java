package com.example.demo.domain.repositories;

import com.example.demo.domain.model.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctorNameAndDate(String name, LocalDate date);
    List<Availability> findByDoctorAvailability(Long doctorId);
}
