package ru.yofujitsu.otp_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yofujitsu.otp_project.dto.OtpConfigUpdateDto;
import ru.yofujitsu.otp_project.entity.User;
import ru.yofujitsu.otp_project.service.AdminService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Controller", description = "Admin operations")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Update OTP config")
    @PostMapping("/update-config")
    public void updateConfig(@RequestBody OtpConfigUpdateDto dto) {
        adminService.updateConfig(dto);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestParam UUID userId) {
        adminService.deleteUser(userId);
    }
}
