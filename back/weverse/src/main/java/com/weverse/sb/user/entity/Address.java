package com.weverse.sb.user.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address",
       indexes = {
         @Index(name = "idx_address_user_id", columnList = "user_id"),
         @Index(name = "idx_address_is_default", columnList = "is_default")
       })
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false) // (확실하지 않음) User PK 컬럼명이 user_id가 맞나요?
    private User user;

    // 수취인 영문 성/이름
    @Column(nullable = false, length = 50)
    private String familyName;

    @Column(nullable = false, length = 50)
    private String givenName;

    // 국가 코드 (예: KR, US)
    @Column(nullable = false, length = 2)
    private String countryCode;

    // 주소
    @Column(nullable = false, length = 255)
    private String line1;

    @Column(length = 255)
    private String line2;

    @Column(length = 100)
    private String city;             // KR이면 선택

    @Column(length = 100)
    private String stateOrProvince;  // KR이면 선택

    @Column(length = 100)
    private String district;         // 구/군 등 (선택)

    @Column(nullable = false, length = 20)
    private String postalCode;

    // 연락처
    @Column(nullable = false, length = 30)
    private String phone;

    // 기본 배송지 여부
    @Column(nullable = false)
    private boolean isDefault;

    // 개인정보 동의 시각 (동의 체크가 true면 세팅)
    private java.time.LocalDateTime privacyConsentedAt;

    @CreationTimestamp
    private java.time.LocalDateTime createdAt;

    @UpdateTimestamp
    private java.time.LocalDateTime updatedAt;
}

