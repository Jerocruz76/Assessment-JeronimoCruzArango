package com.example.demo.web;

import com.example.demo.application.dto.request.AppointmentRequest;
import com.example.demo.application.dto.response.AppointmentResponse;
import com.example.demo.application.services.impl.AppointmentImpl;
import com.example.demo.application.services.impl.ScheduleService;
import com.example.demo.domain.model.entities.Appointment;
import com.example.demo.domain.model.entities.Availability;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private final AppointmentImpl appointmentImpl;

    @Autowired
    private final ScheduleService scheduleService;

    public AppointmentController(AppointmentImpl appointment, ScheduleService scheduleService) {
        this.appointmentImpl = appointment;
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Create a new appointment", description = "Creates a new appointment with the given details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppointmentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping("/create")
    public AppointmentResponse create(
            @RequestParam String doctorName,
            @RequestParam String date,
            @RequestParam String start,
            @RequestParam String end,
            @RequestBody(required = true) AppointmentRequest request
    ) {
        return scheduleService.createAppointment(
                doctorName, LocalDate.parse(date), LocalTime.parse(start), LocalTime.parse(end), request);
    }

    @Operation(summary = "Get an appointment by ID", description = "Retrieves an appointment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        Appointment response = appointmentImpl.getById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get appointments by doctor name", description = "Retrieves all appointments for a specific doctor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Appointment.class)))
    })
    @GetMapping("/getByDoctorName/{name}")
    public List<Appointment> getByDoctorName(@PathVariable String name) {
        return appointmentImpl.getByDoctorName(name);
    }

    @Operation(summary = "Get appointments by patient name", description = "Retrieves all appointments for a specific patient.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Appointment.class)))
    })
    @GetMapping("/getByPatientName/{name}")
    public List<Appointment> getByPatientName(@PathVariable String name) {
        return appointmentImpl.getByPatientName(name);
    }

    @Operation(summary = "Get availability by doctor name and date",
            description = "Retrieves availability for a specific doctor on a specific date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Availability found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Availability.class)))
    })
    @GetMapping("/getAvailability/{name}/{date}")
    public List<Availability> getAvailability(@PathVariable String name, @PathVariable String date) {
        return scheduleService.obtainAvailability(name, LocalDate.parse(date));
    }

    @Operation(summary = "Get an appointment by reason", description = "Retrieves an appointment based on the reason.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "No appointment found with the specified reason", content = @Content)
    })
    @GetMapping("/getByReason/{reason}")
    public Optional<Appointment> getByReason(@PathVariable String reason) {
        return appointmentImpl.getByReason(reason);
    }

    @Operation(summary = "Get an appointment by date", description = "Retrieves an appointment for a specific date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "No appointment found for the specified date", content = @Content)
    })
    @GetMapping("/getByDate/{date}")
    public Optional<Appointment> getByDate(@PathVariable String date) {
        return appointmentImpl.getByDate(LocalDate.parse(date));
    }
}
