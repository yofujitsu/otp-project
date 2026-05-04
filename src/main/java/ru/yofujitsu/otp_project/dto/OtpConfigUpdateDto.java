package ru.yofujitsu.otp_project.dto;

public record OtpConfigUpdateDto(
        Integer codeLength, Integer ttlSeconds
) {
}
