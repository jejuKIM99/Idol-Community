package com.weverse.sb.entity.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "jelly_balance")
    private Integer jellyBalance;

    @Column(name = "cash_balance")
    private Integer cashBalance;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "is_sms_verified")
    private Boolean isSmsVerified;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
