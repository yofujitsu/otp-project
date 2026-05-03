package ru.yofujitsu.otp_project.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.yofujitsu.otp_project.entity.OtpConfig;
import ru.yofujitsu.otp_project.repository.OtpConfigRepository;

@Configuration
@RequiredArgsConstructor
public class OtpCodeConfig {

    @Value("${otp.length}")
    private int length;
    @Value("${otp.ttl}")
    private int ttlSeconds;

    private final OtpConfigRepository otpConfigRepository;

    @PostConstruct
    public void initConfig() {
        var config = OtpConfig.builder()
                .id(1L)
                .ttlSeconds(ttlSeconds)
                .codeLength(length)
                .build();
        otpConfigRepository.save(config);
    }

}
