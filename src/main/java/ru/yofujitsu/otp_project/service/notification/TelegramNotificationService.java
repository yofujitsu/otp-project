package ru.yofujitsu.otp_project.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yofujitsu.otp_project.entity.User;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service("TG")
@RequiredArgsConstructor
@Slf4j
public class TelegramNotificationService implements CodeNotificationService {

    @Value("${telegram.token}")
    private String token;
    @Value("${telegram.api-url}")
    private String apiUrl;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void sendCode(User user, String code) {

        String message = "Your OTP Verification code: " + code;

        String url = String.format(
                apiUrl + "%s/sendMessage?chat_id=%s&text=%s",
                token,
                user.getTgChatId(),
                URLEncoder.encode(message, StandardCharsets.UTF_8)
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Sent request with OTP code to telegram: {}", request);
        } catch (Exception e) {
            throw new RuntimeException("Request to Telegram API failed", e);
        }
    }
}
