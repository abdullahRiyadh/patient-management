package com.abdullah.patient_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Abdullah Riyadh
 * @date 4/9/26
 */
@Data
public class LoginRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
