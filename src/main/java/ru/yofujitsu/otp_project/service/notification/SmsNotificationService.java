package ru.yofujitsu.otp_project.service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("SMS")
@RequiredArgsConstructor
public class SmsNotificationService implements CodeNotificationService {

    @Override
    public void sendCode(String destination, String code) {

    }
}
