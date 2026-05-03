package ru.yofujitsu.otp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yofujitsu.otp_project.dto.ResponseTokenDto;
import ru.yofujitsu.otp_project.dto.SignInDto;
import ru.yofujitsu.otp_project.dto.SignUpDto;
import ru.yofujitsu.otp_project.entity.User;
import ru.yofujitsu.otp_project.entity.UserRole;
import ru.yofujitsu.otp_project.repository.UserRepository;
import ru.yofujitsu.otp_project.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ResponseTokenDto signUp(SignUpDto signUpDto) {

        if (signUpDto.role() == UserRole.ADMIN && userRepository.existsByRole(UserRole.ADMIN))
            throw new RuntimeException("Admin already exists");

        var user = User.builder()
                .username(signUpDto.username())
                .password(passwordEncoder.encode(signUpDto.password()))
                .role(signUpDto.role())
                .build();

        userRepository.save(user);

        return new ResponseTokenDto(jwtUtil.generateToken(signUpDto.username()));
    }

    public ResponseTokenDto signIn(SignInDto signInDto) {

        var user = userRepository.findByUsername(signInDto.username()).orElseThrow();

        if (!passwordEncoder.matches(signInDto.password(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return new ResponseTokenDto(jwtUtil.generateToken(signInDto.username()));
    }
}
