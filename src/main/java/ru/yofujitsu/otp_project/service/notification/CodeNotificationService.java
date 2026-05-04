package ru.yofujitsu.otp_project.service.notification;

import ru.yofujitsu.otp_project.entity.User;

public interface CodeNotificationService {
    void sendCode(User user, String code);
}
