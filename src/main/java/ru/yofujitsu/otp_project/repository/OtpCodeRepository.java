package ru.yofujitsu.otp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yofujitsu.otp_project.entity.OtpCode;
import ru.yofujitsu.otp_project.entity.OtpStatus;
import ru.yofujitsu.otp_project.entity.User;

import java.util.List;
import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findTopByUserUsernameAndOperationIdAndStatusOrderByCreatedAtDesc(
            String username,
            String operationId,
            OtpStatus status
    );

    List<OtpCode> findAllByStatus(OtpStatus otpStatus);

    void deleteAllByUser(User user);
}