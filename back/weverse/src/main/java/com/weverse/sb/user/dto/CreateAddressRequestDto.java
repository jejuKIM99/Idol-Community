package com.weverse.sb.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class CreateAddressRequestDto {
    // 체크박스
    private Boolean isDefault;

    // 영문 성/이름
    @NotBlank @Size(max = 50) private String familyName;
    @NotBlank @Size(max = 50) private String givenName;

    // 국가
    @NotBlank @Size(min = 2, max = 2) private String countryCode; // ISO-2

    // 주소
    @NotBlank @Size(max = 255) private String line1;
    @Size(max = 255) private String line2;

    // 해외용 필드(한국 아닐 경우 요구)
    @Size(max = 100) private String city;
    @Size(max = 100) private String stateOrProvince;
    @Size(max = 100) private String district;

    @NotBlank @Size(max = 20) private String postalCode;

    // 전화
    @NotBlank @Size(max = 30) private String phone;

    // 개인정보 동의(체크박스)
    @NotNull private Boolean privacyConsent;
}

