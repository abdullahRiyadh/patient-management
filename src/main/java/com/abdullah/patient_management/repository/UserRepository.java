package com.abdullah.patient_management.repository;

import com.abdullah.patient_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Abdullah Riyadh
 * @date 4/9/26
 */
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String  userName);
    Optional<User>findByEmail(String email);

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
