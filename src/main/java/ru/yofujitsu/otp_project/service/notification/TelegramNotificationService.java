package ru.yofujitsu.otp_project.service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TG")
@RequiredArgsConstructor
public class TelegramNotificationService implements CodeNotificationService {

    @Override
    public void sendCode(String destination, String code) {

    }
}
