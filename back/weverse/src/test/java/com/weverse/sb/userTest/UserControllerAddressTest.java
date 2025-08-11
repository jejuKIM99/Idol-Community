package com.weverse.sb.userTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.weverse.sb.user.entity.Address;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.enums.Role;
import com.weverse.sb.user.repository.AddressRepository;
import com.weverse.sb.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class UserControllerAddressTest {

    @Autowired MockMvc mockMvc;
    @Autowired UserRepository userRepository;
    @Autowired AddressRepository addressRepository;

    @WithMockUser(username = "me@test.com", roles = {"USER"})
    @Test
    void 배송지_생성_성공() throws Exception {
        // 준비: 테스트 유저
        User u = User.builder()
                .email("me@test.com")
                .password("encoded") // 인코딩 여부 무관
                .nickname("me")
                .role(Role.ROLE_USER) // hasRole("USER") 사용 시 ROLE_ 접두어 매핑 확인 필요(확실하지 않음)
                .name("Seunghyo Kim")
                .phoneNumber("010-0000-0000")  // NOT NULL
                .country("KR")                 // NOT NULL
                .jellyBalance(0)               // NOT NULL
                .cashBalance(0)                // NOT NULL
                .isEmailVerified(false)        // NOT NULL
                .isSmsVerified(false)          // NOT NULL
                .createdAt(LocalDateTime.now())// NOT NULL
                .build();
        userRepository.save(u);

        String json = """
        		{
        		  "familyName": "HONG",
        		  "givenName": "GILDONG",
        		  "phone": "010-1234-5678",
        		  "postalCode": "06236",
        		  "line1": "서울시 강남구 테헤란로 123",
        		  "line2": "3층",
        		  "isDefault": true,
        		  "countryCode": "KR",
        		  "privacyConsent": true
        		}
        		""";

        mockMvc.perform(post("/api/users/me/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")                 // 인코딩 명시 권장
                .content(json)
                // 인증 활성화 상태라면 @WithMockUser(...) 또는 SecurityContext 주입 필요
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isDefault").value(true))
        .andExpect(jsonPath("$.familyName").value("HONG"))     // ✅ 변경
        .andExpect(jsonPath("$.givenName").value("GILDONG"))   // ✅ 변경
        .andExpect(jsonPath("$.countryCode").value("KR"))
        .andExpect(jsonPath("$.postalCode").value("06236"));
    }

    @WithMockUser(username = "me@test.com", roles = {"USER"}) // ✅ 인증 주입
    @Test
    void 내_배송지_목록_조회() throws Exception {
        // 1) 사용자 픽스처 (이메일은 위 WithMockUser와 일치해야 함)
        User u = userRepository.save(User.builder()
                .email("me@test.com")
                .password("encoded")
                .nickname("me")
                .name("Seunghyo Kim")
                .role(Role.ROLE_USER)        // DB 저장은 ROLE_USER 권장
                .phoneNumber("010-0000-0000")  // NOT NULL
                .country("KR")                 // NOT NULL
                .jellyBalance(0)               // NOT NULL
                .cashBalance(0)                // NOT NULL
                .isEmailVerified(false)        // NOT NULL
                .isSmsVerified(false)          // NOT NULL
                .createdAt(LocalDateTime.now())// NOT NULL
                .build());

        // 2) 주소 픽스처 하나 만들어두기 (목록이 비어도 200이지만, 검증 편하게)
        addressRepository.save(Address.builder()
                .user(u)
                .familyName("HONG")
                .givenName("GILDONG")
                .countryCode("KR")
                .line1("서울시 강남구 테헤란로 123")
                .line2("3층")
                .postalCode("06236")
                .phone("010-1234-5678")
                .isDefault(true)
                .privacyConsentedAt(java.time.LocalDateTime.now())
                .build());

        // 3) 호출 및 검증
        mockMvc.perform(get("/api/users/me/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].familyName").value("HONG"))
                .andExpect(jsonPath("$[0].givenName").value("GILDONG"));
                // 필요하면 .andDo(print());
    }
}
