package com.abdullah.patient_management.repository;

import com.abdullah.patient_management.entity.Appointment;
import com.abdullah.patient_management.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Abdullah Riyadh
 * @date 4/9/26
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByAppointmentDate(LocalDate date);

    List<Appointment> findByStatus(AppointmentStatus status);

    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.appointmentDate = :date AND a.appointmentTime = :time " +
            "AND a.status NOT IN ('CANCELLED', 'NO_SHOW')")
    boolean isDoctorSlotTaken(@Param("doctorId") Long doctorId,
                              @Param("date") LocalDate date,
                              @Param("time") LocalTime time);

    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
}
