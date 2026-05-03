package ru.yofujitsu.otp_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "otp_config")
public class OtpConfig {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer codeLength;
    private Integer ttlSeconds;
}