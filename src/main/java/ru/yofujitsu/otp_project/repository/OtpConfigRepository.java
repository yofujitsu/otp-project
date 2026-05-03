package ru.yofujitsu.otp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yofujitsu.otp_project.entity.OtpConfig;

public interface OtpConfigRepository extends JpaRepository<OtpConfig, Long> {
}