package com.example.demo.application.services.impl;
import com.example.demo.application.services.interfaces.AppointmentService;
import com.example.demo.domain.model.entities.Appointment;
import com.example.demo.domain.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Appointment existingAppointment = appointmentRepository.findById(id).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Appointment with id " + id + " not found")
        );
        appointmentRepository.delete(existingAppointment);
        return true;
    }

    @Override
    @Transactional
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Appointment with id " + id + " not found")
        );
    }

    @Transactional
    public List<Appointment> getByDoctorName(String name) {
        return appointmentRepository.findByDoctorName(name);
    }

    @Transactional
    public List<Appointment> getByPatientName(String name) {
        return appointmentRepository.findByPatientName(name);
    }

    @Transactional
    public Optional<Appointment> getByDate(LocalDate date){
        return appointmentRepository.findByDate(date);
    }

    @Transactional
    public Optional<Appointment> getByReason(String reason){
        return appointmentRepository.findByReason(reason);
    }
}
