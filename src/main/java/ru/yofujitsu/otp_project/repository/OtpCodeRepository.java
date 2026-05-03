package ru.yofujitsu.otp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yofujitsu.otp_project.entity.OtpCode;
import ru.yofujitsu.otp_project.entity.OtpStatus;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findByCodeAndStatus(String code, OtpStatus status);
}