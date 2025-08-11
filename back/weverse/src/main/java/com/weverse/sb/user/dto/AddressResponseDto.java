package com.weverse.sb.user.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class AddressResponseDto {
    private Long addressId;
    private String familyName;
    private String givenName;
    private String countryCode;
    private String line1;
    private String line2;
    private String city;
    private String stateOrProvince;
    private String district;
    private String postalCode;
    private String phone;
    @JsonProperty("isDefault")
    private boolean isDefault;
    private LocalDateTime privacyConsentedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

