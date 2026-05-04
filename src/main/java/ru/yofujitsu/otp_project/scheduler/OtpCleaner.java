package ru.yofujitsu.otp_project.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.yofujitsu.otp_project.entity.OtpCode;
import ru.yofujitsu.otp_project.entity.OtpStatus;
import ru.yofujitsu.otp_project.repository.OtpCodeRepository;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OtpCleaner {

    private final OtpCodeRepository otpcodeRepository;

    @Scheduled(fixedRate = 60_000)
    public void expireCodes() {

        List<OtpCode> activeCodes = otpcodeRepository.findAllByStatus(OtpStatus.ACTIVE);

        Instant now = Instant.now();

        for (OtpCode otp : activeCodes) {
            if (otp.getExpiresAt().isBefore(now)) {
                otp.setStatus(OtpStatus.EXPIRED);
            }
        }

        otpcodeRepository.saveAll(activeCodes);
    }
}
