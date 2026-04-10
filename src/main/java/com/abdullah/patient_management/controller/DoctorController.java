package com.abdullah.patient_management.controller;

import com.abdullah.patient_management.dto.request.DoctorRequest;
import com.abdullah.patient_management.dto.response.ApiResponse;
import com.abdullah.patient_management.dto.response.DoctorResponse;
import com.abdullah.patient_management.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Tag(name = "Doctors", description = "Doctor management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    @Operation(summary = "Add a new doctor")
    public ResponseEntity<ApiResponse<DoctorResponse>> create(
            @Valid @RequestBody DoctorRequest request) {
        DoctorResponse response = doctorService.createDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Doctor added successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get all doctors")
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(doctorService.getAllDoctors()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID")
    public ResponseEntity<ApiResponse<DoctorResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(doctorService.getDoctorById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update doctor details")
    public ResponseEntity<ApiResponse<DoctorResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Doctor updated successfully",
                doctorService.updateDoctor(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a doctor")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(ApiResponse.success("Doctor removed successfully", null));
    }

    @GetMapping("/available")
    @Operation(summary = "Get all available doctors")
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getAvailable() {
        return ResponseEntity.ok(ApiResponse.success(doctorService.getAvailableDoctors()));
    }

    @GetMapping("/specialization/{specialization}")
    @Operation(summary = "Get doctors by specialization")
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getBySpecialization(
            @PathVariable String specialization) {
        return ResponseEntity.ok(ApiResponse.success(
                doctorService.getDoctorsBySpecialization(specialization)));
    }
}
