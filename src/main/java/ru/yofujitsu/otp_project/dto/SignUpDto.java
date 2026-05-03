package ru.yofujitsu.otp_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.yofujitsu.otp_project.entity.UserRole;

public record SignUpDto(@NotBlank String username, @NotBlank String password, @NotNull UserRole role) {
}
