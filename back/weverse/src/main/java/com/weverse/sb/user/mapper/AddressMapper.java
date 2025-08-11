package com.weverse.sb.user.mapper;

import com.weverse.sb.user.dto.AddressResponseDto;
import com.weverse.sb.user.entity.Address;

public class AddressMapper {
    public static AddressResponseDto toDto(Address a) {
        return AddressResponseDto.builder()
                .addressId(a.getAddressId())
                .familyName(a.getFamilyName())
                .givenName(a.getGivenName())
                .countryCode(a.getCountryCode())
                .line1(a.getLine1())
                .line2(a.getLine2())
                .city(a.getCity())
                .stateOrProvince(a.getStateOrProvince())
                .district(a.getDistrict())
                .postalCode(a.getPostalCode())
                .phone(a.getPhone())
                .isDefault(a.isDefault())
                .privacyConsentedAt(a.getPrivacyConsentedAt())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();
    }
}

