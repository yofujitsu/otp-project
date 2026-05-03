package ru.yofujitsu.otp_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yofujitsu.otp_project.dto.ResponseTokenDto;
import ru.yofujitsu.otp_project.dto.SignInDto;
import ru.yofujitsu.otp_project.dto.SignUpDto;
import ru.yofujitsu.otp_project.service.AuthService;
import ru.yofujitsu.otp_project.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseTokenDto signIn(@RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }

    @PostMapping("/sign-up")
    public ResponseTokenDto signUp(@RequestBody SignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }

    @DeleteMapping
    public void deleteAll() {
        userService.deleteAll();
    }
}
