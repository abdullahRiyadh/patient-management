package com.abdullah.patient_management.dto.response;

import com.abdullah.patient_management.entity.Doctor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
@Data
@Builder
public class DoctorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String specialization;
    private String qualification;
    private String licenseNumber;
    private boolean available;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DoctorResponse from(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .fullName("Dr. " + doctor.getFirstName() + " " + doctor.getLastName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .specialization(doctor.getSpecialization())
                .qualification(doctor.getQualification())
                .licenseNumber(doctor.getLicenseNumber())
                .available(doctor.isAvailable())
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
}
