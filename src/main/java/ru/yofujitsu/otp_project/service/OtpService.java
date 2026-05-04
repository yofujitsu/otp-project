package ru.yofujitsu.otp_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yofujitsu.otp_project.dto.OtpCodeCreateDto;
import ru.yofujitsu.otp_project.dto.OtpCodeValidateDto;
import ru.yofujitsu.otp_project.entity.OtpCode;
import ru.yofujitsu.otp_project.entity.OtpStatus;
import ru.yofujitsu.otp_project.repository.OtpCodeRepository;
import ru.yofujitsu.otp_project.repository.OtpConfigRepository;
import ru.yofujitsu.otp_project.repository.UserRepository;
import ru.yofujitsu.otp_project.service.notification.CodeNotificationService;
import ru.yofujitsu.otp_project.util.OtpGenerator;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {

    private final OtpCodeRepository otpCodeRepository;
    private final UserRepository userRepository;
    private final OtpConfigRepository otpConfigRepository;
    private final OtpGenerator otpGenerator;
    private final Map<String, CodeNotificationService> notificationServices;

    public void createOtpCode(OtpCodeCreateDto dto, String username) {

        var config = otpConfigRepository.findById(1L).orElseThrow();
        var user = userRepository.findByUsername(username).orElseThrow();
        var code = otpGenerator.generateCode(config.getCodeLength());
        var now = Instant.now();

        var otp = OtpCode.builder()
                .code(code)
                .status(OtpStatus.ACTIVE)
                .expiresAt(now.plus(config.getTtlSeconds(), TimeUnit.SECONDS.toChronoUnit()))
                .operationId(dto.operationId())
                .user(user)
                .build();

        otpCodeRepository.save(otp);
        log.info("Generated new OTP code: {}", otp);

        var service = notificationServices.get(dto.serviceType().name());
        if(service == null) throw new RuntimeException("Unsupported service");

        service.sendCode(user, code);
    }

    @Transactional
    public boolean validateOtp(OtpCodeValidateDto dto, String username) {

        OtpCode otp = otpCodeRepository
                .findTopByUserUsernameAndOperationIdAndStatusOrderByCreatedAtDesc(
                        username,
                        dto.operationId(),
                        OtpStatus.ACTIVE
                )
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        Instant now = Instant.now();

        if (otp.getExpiresAt().isBefore(now)) {
            otp.setStatus(OtpStatus.EXPIRED);
            otpCodeRepository.save(otp);
            throw new RuntimeException("OTP expired");
        }

        if (!otp.getCode().equals(dto.otpCode()))
            throw new RuntimeException("Invalid OTP");

        otp.setStatus(OtpStatus.USED);
        otpCodeRepository.save(otp);

        return true;
    }


}
