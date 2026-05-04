package ru.yofujitsu.otp_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yofujitsu.otp_project.dto.ResponseTokenDto;
import ru.yofujitsu.otp_project.dto.SignInDto;
import ru.yofujitsu.otp_project.dto.SignUpDto;
import ru.yofujitsu.otp_project.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Users authentication")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Sign in")
    @PostMapping("/sign-in")
    public ResponseTokenDto signIn(@RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }

    @Operation(summary = "Sign up")
    @PostMapping("/sign-up")
    public ResponseTokenDto signUp(@RequestBody SignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }
}
