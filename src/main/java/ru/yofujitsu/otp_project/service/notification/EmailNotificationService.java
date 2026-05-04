package ru.yofujitsu.otp_project.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.yofujitsu.otp_project.entity.User;

@Service("EMAIL")
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService implements CodeNotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendCode(User user, String code) {

        if (user.getEmail() == null)
            throw new RuntimeException("User have no email");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject("OTP Code");
        message.setText("Your OTP verification code: " + code);

        mailSender.send(message);
        log.info("Sent request with OTP code to email: {}", message);
    }
}
