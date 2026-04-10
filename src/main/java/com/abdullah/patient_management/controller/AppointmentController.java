package com.abdullah.patient_management.controller;

import com.abdullah.patient_management.dto.request.AppointmentRequest;
import com.abdullah.patient_management.dto.response.ApiResponse;
import com.abdullah.patient_management.dto.response.AppointmentResponse;
import com.abdullah.patient_management.entity.AppointmentStatus;
import com.abdullah.patient_management.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "Appointment scheduling and management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @Operation(summary = "Book a new appointment")
    public ResponseEntity<ApiResponse<AppointmentResponse>> book(
            @Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse response = appointmentService.bookAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Appointment booked successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get all appointments")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(appointmentService.getAllAppointments()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID")
    public ResponseEntity<ApiResponse<AppointmentResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(
                appointmentService.getAppointmentById(id)));
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all appointments for a patient")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getByPatient(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(ApiResponse.success(
                appointmentService.getAppointmentsByPatient(patientId)));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get all appointments for a doctor")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getByDoctor(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(ApiResponse.success(
                appointmentService.getAppointmentsByDoctor(doctorId)));
    }

    @GetMapping("/date/{date}")
    @Operation(summary = "Get all appointments on a specific date")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(ApiResponse.success(
                appointmentService.getAppointmentsByDate(date)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update appointment status")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam AppointmentStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Status updated successfully",
                appointmentService.updateStatus(id, status)));
    }

    @DeleteMapping("/{id}/cancel")
    @Operation(summary = "Cancel an appointment")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(ApiResponse.success("Appointment cancelled successfully", null));
    }
}
