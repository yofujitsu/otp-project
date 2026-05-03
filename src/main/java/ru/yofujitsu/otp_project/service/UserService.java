package ru.yofujitsu.otp_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yofujitsu.otp_project.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
