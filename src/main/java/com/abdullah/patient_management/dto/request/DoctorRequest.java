package com.abdullah.patient_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
@Data
public class DoctorRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    private String qualification;

    private String licenseNumber;

    private boolean available = true;
}
