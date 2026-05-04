package ru.yofujitsu.otp_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    private String username;
    private String password;
    private String email;
    private String phone;
    private String tgChatId;
    @Enumerated(EnumType.STRING)
    private UserRole role;

}