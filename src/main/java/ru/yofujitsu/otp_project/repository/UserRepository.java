package ru.yofujitsu.otp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yofujitsu.otp_project.entity.User;
import ru.yofujitsu.otp_project.entity.UserRole;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    boolean existsByRole(UserRole role);
}