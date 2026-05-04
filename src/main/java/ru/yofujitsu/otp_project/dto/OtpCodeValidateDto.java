package ru.yofujitsu.otp_project.dto;

import jakarta.validation.constraints.NotBlank;

public record OtpCodeValidateDto(@NotBlank String operationId, @NotBlank String otpCode) {
}
