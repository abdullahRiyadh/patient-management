package com.abdullah.patient_management.exception;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found with id: " + id);
    }
}
