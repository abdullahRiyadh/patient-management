package com.abdullah.patient_management.exception;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message) {
        super(message);
    }
}
