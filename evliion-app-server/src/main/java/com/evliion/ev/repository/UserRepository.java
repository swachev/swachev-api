package com.evliion.ev.repository;

import com.evliion.ev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumberOrEmail(String mobileNumber, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByMobileNumber(String mobileNumber);

    Boolean existsByMobileNumber(String mobileNumber);

    Boolean existsByEmail(String email);
}
