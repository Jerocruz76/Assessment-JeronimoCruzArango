package com.example.demo.application.mappers;

import com.example.demo.application.dto.request.AppointmentRequest;
import com.example.demo.application.dto.response.AppointmentResponse;
import com.example.demo.domain.model.entities.Appointment;
import com.example.demo.domain.model.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(source = "doctorId", target = "doctorId")
    Appointment toEntity(AppointmentRequest appointmentRequest);

    @Mapping(source = "doctorId", target = "doctorId")
    AppointmentResponse toResponse(Appointment appointment);

    default Long map(UserEntity userEntity){
        return userEntity != null ? userEntity.getId() : null;
    }
}
