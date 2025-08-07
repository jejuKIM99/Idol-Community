package com.weverse.sb.user.entity;

import java.time.LocalDateTime;

import com.weverse.sb.user.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role; // ROLE_USER, ROLE_ADMIN

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname;

    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @Column(name = "profile_image", length = 2083)
    private String profileImage;

    @Column(name = "jelly_balance", nullable = false)
    private Integer jellyBalance = 0;

    @Column(name = "cash_balance", nullable = false)
    private Integer cashBalance = 0;

    @Column(name = "is_email_verified", nullable = false)
    private Boolean isEmailVerified = false;

    @Column(name = "is_sms_verified", nullable = false)
    private Boolean isSmsVerified = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}

