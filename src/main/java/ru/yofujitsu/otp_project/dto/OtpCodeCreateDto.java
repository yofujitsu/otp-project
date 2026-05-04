package ru.yofujitsu.otp_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.yofujitsu.otp_project.entity.ServiceType;

public record OtpCodeCreateDto(@NotBlank String operationId, @NotNull ServiceType serviceType) {
}
