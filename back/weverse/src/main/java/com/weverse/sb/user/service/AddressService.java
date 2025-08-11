package com.weverse.sb.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.weverse.sb.user.dto.AddressResponseDto;
import com.weverse.sb.user.dto.CreateAddressRequestDto;
import com.weverse.sb.user.entity.Address;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.mapper.AddressMapper;
import com.weverse.sb.user.repository.AddressRepository;
import com.weverse.sb.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. email=" + email));
    }

    @Transactional
    public AddressResponseDto createMyAddress(String email, CreateAddressRequestDto req) {
        // 1) 개인정보 동의 필수
        if (Boolean.FALSE.equals(req.getPrivacyConsent())) {
            // 전역 예외 처리로 400 내려가도록 IllegalArgumentException 사용(확실하지 않음: 팀 표준 예외 클래스 있으면 교체)
            throw new IllegalArgumentException("개인정보 수집·이용 동의가 필요합니다.");
        }

        // 2) 국가별 필수값 보강
        final String cc = req.getCountryCode().toUpperCase();
        if (!cc.matches("^[A-Z]{2}$")) {
            throw new IllegalArgumentException("countryCode는 ISO-2 형식이어야 합니다.");
        }
        if (!"KR".equals(cc)) {
            // 해외: city, stateOrProvince 권장 → 여기선 필수 처리
            if (isBlank(req.getCity()) || isBlank(req.getStateOrProvince())) {
                throw new IllegalArgumentException("해외 주소는 city/stateOrProvince가 필요합니다.");
            }
        }
        if (isBlank(req.getPostalCode())) {
            // 한국은 프론트 자동입력이더라도 서버 검증은 유지
            throw new IllegalArgumentException("postalCode는 필수입니다.");
        }

        User me = getUserByEmail(email);

        // 3) 기본 배송지 보장: 신규가 기본이면 기존 기본 해제
        boolean wantDefault = Boolean.TRUE.equals(req.getIsDefault());
        if (wantDefault) {
            addressRepository.findByUserAndIsDefaultTrue(me).ifPresent(old -> {
                old.setDefault(false);
                addressRepository.save(old);
            });
        }

        Address saved = addressRepository.save(
            Address.builder()
                    .user(me)
                    .familyName(req.getFamilyName().trim())
                    .givenName(req.getGivenName().trim())
                    .countryCode(cc)
                    .line1(req.getLine1().trim())
                    .line2(trimToNull(req.getLine2()))
                    .city(trimToNull(req.getCity()))
                    .stateOrProvince(trimToNull(req.getStateOrProvince()))
                    .district(trimToNull(req.getDistrict()))
                    .postalCode(req.getPostalCode().trim())
                    .phone(normalizePhone(req.getPhone())) // (확실하지 않음) 국제 포맷 변환 여부
                    .isDefault(wantDefault)
                    .privacyConsentedAt(java.time.LocalDateTime.now())
                    .build()
        );

        return AddressMapper.toDto(saved);
    }

    public List<AddressResponseDto> getMyAddresses(String email) {
        User me = getUserByEmail(email);
        return addressRepository.findAllByUserOrderByCreatedAtDesc(me)
                .stream().map(AddressMapper::toDto).toList();
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
    private static String trimToNull(String s) {
        return isBlank(s) ? null : s.trim();
    }
    private static String normalizePhone(String raw) {
        // (확실하지 않음) 팀 정책: E.164 강제? 여기선 공백 제거만.
        return raw == null ? null : raw.replaceAll("\\s+", "");
    }
}

