package com.weverse.sb.payment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weverse.sb.payment.controller.JellyController;
import com.weverse.sb.payment.dto.JellyChargeReadyResponseDTO;
import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.dto.JellyProductResponseDTO;
import com.weverse.sb.payment.service.JellyPaymentProcessor;
import com.weverse.sb.payment.service.JellyService;
import com.weverse.sb.payment.service.PaymentVerificationService;

@WebMvcTest(JellyController.class)
class JellyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JellyService jellyService;

    @MockBean
    private PaymentVerificationService paymentVerificationService;

    @MockBean
    private JellyPaymentProcessor jellyPaymentProcessor;

    private JellyProductResponseDTO testProduct;
    private JellyChargeReadyResponseDTO testChargeReadyResponse;

    @BeforeEach
    void setUp() {
        // 테스트용 젤리 상품 데이터
        testProduct = JellyProductResponseDTO.builder()
                .productId(1L)
                .productName("젤리 20")
                .price(new BigDecimal("6000"))
                .jellyAmount(20)
                .build();

        // 테스트용 결제 준비 응답 데이터
        testChargeReadyResponse = JellyChargeReadyResponseDTO.builder()
                .merchantUid("jelly_test_123")
                .productName("젤리 20")
                .amount(new BigDecimal("6000"))
                .buyerName("테스트 사용자")
                .buyerEmail("test@example.com")
                .build();
    }

    @Test
    void getJellyProducts_ShouldReturnProductList() throws Exception {
        // Given
        List<JellyProductResponseDTO> products = Arrays.asList(testProduct);
        when(jellyService.getJellyProductList()).thenReturn(products);

        // When & Then
        mockMvc.perform(get("/api/jelly/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].productName").value("젤리 20"))
                .andExpect(jsonPath("$[0].price").value(6000))
                .andExpect(jsonPath("$[0].jellyAmount").value(20));
    }

    @Test
    void chargeJelly_WhenImpUidIsNull_ShouldPrepareCharge() throws Exception {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        // Reflection을 사용하여 private 필드 설정
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 1L);
            
            java.lang.reflect.Field paymentMethodField = JellyChargeRequestDTO.class.getDeclaredField("paymentMethod");
            paymentMethodField.setAccessible(true);
            paymentMethodField.set(requestDto, "CREDIT_CARD");
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }
        // impUid는 null (결제 준비 단계)

        when(jellyService.prepareCharge(any(JellyChargeRequestDTO.class), anyLong()))
                .thenReturn(testChargeReadyResponse);

        // When & Then
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merchantUid").value("jelly_test_123"))
                .andExpect(jsonPath("$.productName").value("젤리 20"))
                .andExpect(jsonPath("$.amount").value(6000))
                .andExpect(jsonPath("$.buyerName").value("테스트 사용자"))
                .andExpect(jsonPath("$.buyerEmail").value("test@example.com"));
    }

    @Test
    void chargeJelly_WhenImpUidExists_ShouldVerifyPayment() throws Exception {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        // Reflection을 사용하여 private 필드 설정
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 1L);
            
            java.lang.reflect.Field paymentMethodField = JellyChargeRequestDTO.class.getDeclaredField("paymentMethod");
            paymentMethodField.setAccessible(true);
            paymentMethodField.set(requestDto, "CREDIT_CARD");
            
            java.lang.reflect.Field impUidField = JellyChargeRequestDTO.class.getDeclaredField("impUid");
            impUidField.setAccessible(true);
            impUidField.set(requestDto, "imp_test_123");
            
            java.lang.reflect.Field merchantUidField = JellyChargeRequestDTO.class.getDeclaredField("merchantUid");
            merchantUidField.setAccessible(true);
            merchantUidField.set(requestDto, "jelly_test_123");
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        // When & Then
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void chargeJelly_WhenExceptionOccurs_ShouldReturnBadRequest() throws Exception {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        // Reflection을 사용하여 private 필드 설정
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 1L);
            
            java.lang.reflect.Field paymentMethodField = JellyChargeRequestDTO.class.getDeclaredField("paymentMethod");
            paymentMethodField.setAccessible(true);
            paymentMethodField.set(requestDto, "CREDIT_CARD");
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        when(jellyService.prepareCharge(any(JellyChargeRequestDTO.class), anyLong()))
                .thenThrow(new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // When & Then
        mockMvc.perform(post("/api/jelly/charge")
                .param("userId", "999") // 존재하지 않는 사용자 ID
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("사용자를 찾을 수 없습니다."));
    }
} 