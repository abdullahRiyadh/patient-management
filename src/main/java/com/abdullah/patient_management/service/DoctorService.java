package com.abdullah.patient_management.service;


import com.abdullah.patient_management.dto.request.DoctorRequest;
import com.abdullah.patient_management.dto.response.DoctorResponse;
import com.abdullah.patient_management.entity.Doctor;
import com.abdullah.patient_management.exception.DuplicateResourceException;
import com.abdullah.patient_management.exception.ResourceNotFoundException;
import com.abdullah.patient_management.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Abdullah Riyadh
 * @date 4/10/26
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorResponse createDoctor(DoctorRequest request) {
        if (doctorRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Doctor already exists with email: " + request.getEmail());
        }

        Doctor doctor = Doctor.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .specialization(request.getSpecialization())
                .qualification(request.getQualification())
                .licenseNumber(request.getLicenseNumber())
                .available(request.isAvailable())
                .build();

        return DoctorResponse.from(doctorRepository.save(doctor));
    }

    @Transactional(readOnly = true)
    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", id));
        return DoctorResponse.from(doctor);
    }

    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", id));

        if (!doctor.getEmail().equals(request.getEmail()) &&
                doctorRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already in use: " + request.getEmail());
        }

        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setAvailable(request.isAvailable());

        return DoctorResponse.from(doctorRepository.save(doctor));
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor", id);
        }
        doctorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DoctorResponse> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization).stream()
                .map(DoctorResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DoctorResponse> getAvailableDoctors() {
        return doctorRepository.findByAvailableTrue().stream()
                .map(DoctorResponse::from)
                .collect(Collectors.toList());
    }

}
