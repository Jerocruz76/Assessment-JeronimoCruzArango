package com.example.demo.application.services.interfaces;

import com.example.demo.application.dto.request.AppointmentRequest;
import com.example.demo.application.dto.response.AppointmentResponse;
import com.example.demo.application.services.crud.*;
import com.example.demo.domain.model.entities.Appointment;

public interface AppointmentService extends
        GetById<Appointment, Long>,
        Delete<Long> {
}
