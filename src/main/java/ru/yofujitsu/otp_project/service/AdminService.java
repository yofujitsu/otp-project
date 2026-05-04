package ru.yofujitsu.otp_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yofujitsu.otp_project.dto.OtpConfigUpdateDto;
import ru.yofujitsu.otp_project.entity.OtpConfig;
import ru.yofujitsu.otp_project.entity.User;
import ru.yofujitsu.otp_project.entity.UserRole;
import ru.yofujitsu.otp_project.repository.OtpCodeRepository;
import ru.yofujitsu.otp_project.repository.OtpConfigRepository;
import ru.yofujitsu.otp_project.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final OtpConfigRepository configRepository;
    private final UserRepository userRepository;
    private final OtpCodeRepository otpCodeRepository;

    public void updateConfig(OtpConfigUpdateDto dto) {

        OtpConfig config = configRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Config not found"));

        if (dto.codeLength() != null) config.setCodeLength(dto.codeLength());
        if (dto.ttlSeconds() != null) config.setTtlSeconds(dto.ttlSeconds());

        configRepository.save(config);
        log.info("Admin updated OTP config: {}", config);
    }

    public List<User> getAllUsers() {
        log.info("Admin requested for all users");
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getRole() != UserRole.ADMIN)
                .toList();
    }

    @Transactional
    public void deleteUser(UUID userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        otpCodeRepository.deleteAllByUser(user);
        userRepository.delete(user);
        log.info("Admin deleted User with ID: {}", userId);
    }
}
