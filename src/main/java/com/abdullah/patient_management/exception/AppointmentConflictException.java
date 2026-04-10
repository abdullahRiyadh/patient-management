package com.abdullah.patient_management.exception;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
public class AppointmentConflictException extends RuntimeException{
    public AppointmentConflictException(String message) {
        super(message);
    }
}
