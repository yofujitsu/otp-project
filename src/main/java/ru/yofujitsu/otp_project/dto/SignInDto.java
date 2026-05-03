package ru.yofujitsu.otp_project.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDto(String username, @NotBlank String password) {
}
