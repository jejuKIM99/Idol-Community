package com.weverse.sb.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.weverse.sb.payment.dto.JellyChargeReadyResponseDTO;
import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.dto.JellyChargeResponseDTO;
import com.weverse.sb.payment.dto.JellyProductResponseDTO;
import com.weverse.sb.payment.entity.JellyHistory;
import com.weverse.sb.payment.entity.JellyProduct;
import com.weverse.sb.payment.entity.JellyPurchase;
import com.weverse.sb.payment.entity.Payment;
import com.weverse.sb.payment.repository.JellyHistoryRepository;
import com.weverse.sb.payment.repository.JellyProductRepository;
import com.weverse.sb.payment.repository.JellyPurchaseRepository;
import com.weverse.sb.payment.repository.PaymentRepository;
import com.weverse.sb.payment.service.JellyService;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class JellyServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JellyProductRepository jellyProductRepository;

    @Mock
    private JellyPurchaseRepository jellyPurchaseRepository;

    @Mock
    private JellyHistoryRepository jellyHistoryRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private JellyService jellyService;

    private User testUser;
    private JellyProduct testProduct;
    private JellyPurchase testPurchase;
    private Payment testPayment;

    @BeforeEach
    void setUp() {
        // 테스트용 사용자 데이터
        testUser = User.builder()
                .userId(1L)
                .name("테스트 사용자")
                .email("test@example.com")
                .jellyBalance(100)
                .build();

        // 테스트용 젤리 상품 데이터
        testProduct = JellyProduct.builder()
                .jellyProductId(1L)
                .productName("젤리 20")
                .price(new BigDecimal("6000"))
                .jellyAmount(20)
                .bonusJelly(1)
                .benefitDescription("5% 혜택")
                .build();

        // 테스트용 젤리 구매 데이터
        testPurchase = JellyPurchase.builder()
                .jellyPurchaseId(1L)
                .user(testUser)
                .jellyProduct(testProduct)
                .amount(new BigDecimal("6000"))
                .merchantUid("jelly_test_123")
                .status("PENDING")
                .build();

        // 테스트용 결제 데이터
        testPayment = Payment.builder()
                .paymentId(1L)
                .user(testUser)
                .amount(new BigDecimal("6000"))
                .paymentMethod("CREDIT_CARD")
                .status("PAID")
                .impUid("imp_test_123")
                .merchantUid("jelly_test_123")
                .currency("KRW")
                .paidAt(LocalDateTime.now())
                .build();
    }

    @Test
    void getJellyProductList_ShouldReturnProductList() {
        // Given
        List<JellyProduct> products = Arrays.asList(testProduct);
        when(jellyProductRepository.findAll()).thenReturn(products);

        // When
        List<JellyProductResponseDTO> result = jellyService.getJellyProductList();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getProductId());
        assertEquals("젤리 20", result.get(0).getProductName());
        assertEquals(new BigDecimal("6000"), result.get(0).getPrice());
        assertEquals(20, result.get(0).getJellyAmount());
    }

    @Test
    void prepareCharge_ShouldCreatePurchaseAndReturnResponse() {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        // Reflection을 사용하여 private 필드 설정
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 1L);
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(jellyProductRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(jellyPurchaseRepository.save(any(JellyPurchase.class))).thenReturn(testPurchase);

        // When
        JellyChargeReadyResponseDTO result = jellyService.prepareCharge(requestDto, 1L);

        // Then
        assertNotNull(result);
        assertNotNull(result.getMerchantUid());
        assertEquals("젤리 20", result.getProductName());
        assertEquals(new BigDecimal("6000"), result.getAmount());
        assertEquals("테스트 사용자", result.getBuyerName());
        assertEquals("test@example.com", result.getBuyerEmail());

        verify(jellyPurchaseRepository, times(1)).save(any(JellyPurchase.class));
    }

    @Test
    void prepareCharge_WhenUserNotFound_ShouldThrowException() {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 1L);
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            jellyService.prepareCharge(requestDto, 999L);
        });
    }

    @Test
    void prepareCharge_WhenProductNotFound_ShouldThrowException() {
        // Given
        JellyChargeRequestDTO requestDto = new JellyChargeRequestDTO();
        try {
            java.lang.reflect.Field productIdField = JellyChargeRequestDTO.class.getDeclaredField("productId");
            productIdField.setAccessible(true);
            productIdField.set(requestDto, 999L);
        } catch (Exception e) {
            throw new RuntimeException("테스트 데이터 설정 실패", e);
        }

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(jellyProductRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            jellyService.prepareCharge(requestDto, 1L);
        });
    }

    @Test
    void finalizeJellyCharge_ShouldUpdateUserBalanceAndCreateHistory() {
        // Given
        Payment paymentData = new Payment();
        paymentData.setImpUid("imp_test_123");
        paymentData.setAmount(new BigDecimal("6000"));
        paymentData.setPayMethod("CREDIT_CARD");

        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        // When
        JellyChargeResponseDTO result = jellyService.finalizeJellyCharge(paymentData, testPurchase);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getPurchaseId());
        assertEquals("젤리 20", result.getProductName());
        assertEquals(21, result.getChargedJelly()); // 20 + 1 (보너스)
        assertEquals(121, result.getBalanceAfter()); // 100 + 21

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(jellyHistoryRepository, times(2)).save(any(JellyHistory.class)); // 기본 젤리 + 보너스 젤리
    }
} 