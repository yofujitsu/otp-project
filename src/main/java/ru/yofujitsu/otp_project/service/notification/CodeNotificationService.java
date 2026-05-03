package ru.yofujitsu.otp_project.service.notification;

public interface CodeNotificationService {
    void sendCode(String destination, String code);
}
