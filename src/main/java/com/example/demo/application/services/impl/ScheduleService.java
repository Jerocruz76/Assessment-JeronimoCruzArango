package com.example.demo.application.services.impl;

import com.example.demo.application.dto.request.AppointmentRequest;
import com.example.demo.application.dto.response.AppointmentResponse;
import com.example.demo.application.mappers.AppointmentMapper;
import com.example.demo.domain.model.entities.Appointment;
import com.example.demo.domain.model.entities.Availability;
import com.example.demo.domain.repositories.AppointmentRepository;
import com.example.demo.domain.repositories.AvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final AppointmentRepository appointmentRepository;
    private final AvailabilityRepository availabilityRepository;

    public List<Availability> obtainAvailability(String doctorName, LocalDate date){
        return availabilityRepository.findByDoctorNameAndDate(doctorName, date);
    }

    public AppointmentResponse createAppointment(String doctorName, LocalDate date, LocalTime start, LocalTime end, AppointmentRequest request){
        List<Availability> availabilities = availabilityRepository.findByDoctorNameAndDate(doctorName, date);

        boolean available = availabilities.stream()
                .anyMatch(d -> d.getStart().equals(start) && d.getEnd().equals(end) && d.getAvailability());

        if (!available) throw new IllegalArgumentException("The current date is not available");

        Appointment appointment = Appointment.builder()
                .doctorId(request.getDoctorId())
                .patientId(request.getPatientId())
                .reason(request.getReason())
                .description(request.getDescription())
                .appointmentDate(request.getDate())
                .appointmentStart(request.getAppointmentStart())
                .appointmentEnd(request.getAppointmentEnd())
                .build();
        appointmentRepository.save(appointment);

        availabilities.stream()
                .filter(d -> d.getStart().equals(start) && d.getEnd().equals(end))
                .forEach(d -> d.setAvailability(false));

        return AppointmentMapper.INSTANCE.toResponse(appointment);
    }

}
