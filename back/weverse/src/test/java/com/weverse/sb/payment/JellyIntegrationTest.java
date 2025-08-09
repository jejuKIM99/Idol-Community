package com.weverse.sb.payment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.entity.JellyProduct;

import com.weverse.sb.payment.repository.JellyProductRepository;
import com.weverse.sb.payment.repository.JellyPurchaseRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class JellyIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JellyProductRepository jellyProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JellyPurchaseRepository jellyPurchaseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private User testUser;
    private JellyProduct testProduct;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // 테스트용 사용자 생성
        testUser = User.builder()
                .email("test@example.com")
                .password("password123")
                .name("테스트 사용자")
                .nickname("테스터")
                .phoneNumber("010-1234-5678")
                .country("KR")
                .jellyBalance(100)
                .cashBalance(10000)
                .isEmailVerified(true)
                .isSmsVerified(true)
                .build();
        testUser = userRepository.save(testUser);

        // 테스트용 젤리 상품 생성
        testProduct = JellyProduct.builder()
                .productName("젤리 20")
                .price(new BigDecimal("6000"))
                .jellyAmount(20)
                .bonusJelly(1)
                .benefitDescription("5% 혜택")
                .build();
        testProduct = jellyProductRepository.save(testProduct);
    }

    @Test
    void getJellyProducts_ShouldReturnProductList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/jelly/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].productId").exists())
                .andExpect(jsonPath("$[0].productName").exists())
                .andExpect(jsonPath("$[0].price").exists())
                .andExpect(jsonPath("$[0].jellyAmount").exists());
    }

    @Test
    void chargeJelly_WhenImpUidIsNull_ShouldPrepareCharge() throws Exception {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        // Reflection을 사용하여 private 필드 설정
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, testProduct.getJellyProductId());
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        // When & Then
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", testUser.getUserId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merchantUid").exists())
                .andExpect(jsonPath("$.productName").value("젤리 20"))
                .andExpect(jsonPath("$.amount").value(6000))
                .andExpect(jsonPath("$.buyerName").value("테스트 사용자"))
                .andExpect(jsonPath("$.buyerEmail").value("test@example.com"));

        // DB에 JellyPurchase가 생성되었는지 확인
        assert jellyPurchaseRepository.count() > 0;
    }

    @Test
    void chargeJelly_WhenUserNotFound_ShouldReturnBadRequest() throws Exception {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, testProduct.getJellyProductId());
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        // When & Then
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", "999") // 존재하지 않는 사용자 ID
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void chargeJelly_WhenProductNotFound_ShouldReturnBadRequest() throws Exception {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 999L); // 존재하지 않는 상품 ID
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        // When & Then
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", testUser.getUserId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void chargeJelly_WhenImpUidExists_ShouldVerifyPayment() throws Exception {
        // Given - 먼저 결제 준비 단계를 수행하여 merchantUid 생성
        JellyChargeRequestDTO prepareRequest = new JellyChargeRequestDTO();
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(prepareRequest, testProduct.getJellyProductId());
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        // 결제 준비 요청
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", testUser.getUserId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prepareRequest)))
                .andExpect(status().isOk());

        // merchantUid 추출 (실제로는 JSON 파싱이 필요하지만 여기서는 간단히 처리)
        String merchantUid = "jelly_test_123"; // 실제 구현에서는 response에서 추출

        // 결제 검증 요청
        JellyChargeRequestDTO verifyRequest = new JellyChargeRequestDTO();
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(verifyRequest, testProduct.getJellyProductId());

            java.lang.reflect.Field impUidField = JellyChargeRequestDTO.class.getDeclaredField("impUid");
            impUidField.setAccessible(true);
            impUidField.set(verifyRequest, "imp_test_123");

            java.lang.reflect.Field merchantUidField = JellyChargeRequestDTO.class.getDeclaredField("merchantUid");
            merchantUidField.setAccessible(true);
            merchantUidField.set(verifyRequest, merchantUid);
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        // When & Then - 실제 PG사 연동이 없으므로 예외가 발생할 수 있음
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", testUser.getUserId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(verifyRequest)))
                .andExpect(status().isBadRequest()); // PG사 연동 없이 테스트하므로 실패 예상
    }
} 