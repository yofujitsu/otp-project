package ru.yofujitsu.otp_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yofujitsu.otp_project.dto.OtpCodeCreateDto;
import ru.yofujitsu.otp_project.dto.OtpCodeValidateDto;
import ru.yofujitsu.otp_project.service.OtpService;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
@Tag(name = "OTP Controller", description = "Operations with OTP: one-time passwords")
public class OtpController {

    private final OtpService otpService;

    @Operation(summary = "Generate OTP", description = "Creates and sends OTP code via selected service")
    @PostMapping("/generate")
    public void generateOtpCode(@RequestBody OtpCodeCreateDto dto, Authentication authentication) {
        var username = authentication.getName();
        otpService.createOtpCode(dto, username);
    }

    @Operation(summary = "Validate OTP", description = "Validates OTP code for operation")
    @PostMapping("/validate")
    public boolean validateOtpCode(@RequestBody OtpCodeValidateDto dto, Authentication authentication) {
        var username = authentication.getName();
        return otpService.validateOtp(dto, username);
    }
}
