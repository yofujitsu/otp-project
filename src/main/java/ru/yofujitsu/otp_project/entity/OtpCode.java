package ru.yofujitsu.otp_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otp_code")
public class OtpCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String code;
    @Enumerated(EnumType.STRING)
    private OtpStatus status;
    @CreationTimestamp
    private Instant createdAt;
    private Instant expiresAt;
    private String operationId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}