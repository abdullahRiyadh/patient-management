package com.abdullah.patient_management.repository;

import com.abdullah.patient_management.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Abdullah Riyadh
 * @date 4/9/26
 */
public interface DoctorRepository  extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Doctor> findBySpecializationIgnoreCase(String specialization);
    List<Doctor> findByAvailableTrue();
}
