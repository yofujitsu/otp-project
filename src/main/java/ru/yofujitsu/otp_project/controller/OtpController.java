package ru.yofujitsu.otp_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.yofujitsu.otp_project.dto.OtpCodeCreateDto;
import ru.yofujitsu.otp_project.service.OtpService;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate")
    public void generateOtpCode(@RequestBody OtpCodeCreateDto dto) {
        otpService.createOtpCode(dto);
    }
}
