package ru.yofujitsu.otp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yofujitsu.otp_project.dto.OtpCodeCreateDto;
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
public class OtpService {

    private final OtpCodeRepository otpCodeRepository;
    private final UserRepository userRepository;
    private final OtpConfigRepository otpConfigRepository;
    private final OtpGenerator otpGenerator;
    private final Map<String, CodeNotificationService> notificationServices;

    public void createOtpCode(OtpCodeCreateDto dto) {

        var config = otpConfigRepository.findById(1L).orElseThrow();
        var user = userRepository.findByUsername(dto.username()).orElseThrow();
        var code = otpGenerator.generateCode(config.getCodeLength());
        var now = Instant.now();

        var otp = OtpCode.builder()
                .code(code)
                .status(OtpStatus.ACTIVE)
                .expiresAt(now.plus(config.getTtlSeconds(), TimeUnit.SECONDS.toChronoUnit()))
                .operationId(dto.operationId())
                .build();

        otpCodeRepository.save(otp);

        var service = notificationServices.get(dto.serviceType().name());
        if(service == null) throw new RuntimeException("Unsupported service");

        service.sendCode(user.getUsername(), code);
    }


}
