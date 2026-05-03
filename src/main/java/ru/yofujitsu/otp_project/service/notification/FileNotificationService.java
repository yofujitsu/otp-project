package ru.yofujitsu.otp_project.service.notification;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service("FILE")
public class FileNotificationService implements CodeNotificationService {

    @Override
    public void sendCode(String destination, String code) {
        try (FileWriter writer = new FileWriter("otp_codes.txt", true)) {
            writer.write("To: " + destination + " Code: " + code + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write OTP to file", e);
        }
    }
}
