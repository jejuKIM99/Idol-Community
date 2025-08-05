package com.weverse.sb.payment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 주입
public class JellyService {
	
	private final UserRepository userRepository;
    private final JellyProductRepository jellyProductRepository;
    private final JellyPurchaseRepository jellyPurchaseRepository;
    private final JellyHistoryRepository jellyHistoryRepository;
    private final PaymentRepository paymentRepository;

    @Transactional(readOnly = true) // 조회 전용 트랜잭션
    public List<JellyProductResponseDTO> getJellyProductList() {
        return jellyProductRepository.findAll().stream()
            .map(JellyProductResponseDTO::fromEntity) 
            .collect(Collectors.toList());
        
    }
    
    @Transactional // 데이터 변경이 여러 번 일어나므로 트랜잭션 필수!
    public JellyChargeResponseDTO chargeJelly(Long userId, JellyChargeRequestDTO requestDto) {
        // 1. 사용자 및 상품 조회
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found")); // 적절한 예외 처리 필요
        JellyProduct product = jellyProductRepository.findById(requestDto.getProductId())
            .orElseThrow(() -> new RuntimeException("Jelly product not found"));

        // 2. (가상)결제 처리
        Payment payment = Payment.builder()
        	.user(user) 
            .amount(product.getPrice())
            .paymentMethod(requestDto.getPaymentMethod())
            .status("PAID")
            .currency("KRW")
            .build();
        paymentRepository.save(payment);

        // 3. 젤리 구매 내역 생성
        JellyPurchase purchase = JellyPurchase.builder()
            .user(user)
            .jellyProduct(product)
            .payment(payment)
            .paidAt(LocalDateTime.now())
            .build();
        jellyPurchaseRepository.save(purchase);

        // [수정 1] 사용자 젤리 잔액 업데이트 로직 변경
        int balanceBefore = user.getJellyBalance();
        int baseJelly = product.getJellyAmount();
        // 보너스 젤리가 null일 경우를 대비하여 0으로 처리
        int bonusJelly = (product.getBonusJelly() != null) ? product.getBonusJelly() : 0;
        int totalAddedJelly = baseJelly + bonusJelly;
        int balanceAfter = balanceBefore + totalAddedJelly;
        user.setJellyBalance(balanceAfter);
        // userRepository.save(user); // @Transactional 어노테이션 덕분에 마지막에 한번만 저장해도 변경사항이 반영됩니다. (더티 체킹)

        // [수정 2] 젤리 변동 내역(History) 기록 방식 변경
        // 5-1. 기본 젤리 충전 내역 기록
        JellyHistory chargeHistory = JellyHistory.builder()
            .user(user)
            .changeAmount(baseJelly)
            .balanceAfter(balanceBefore + baseJelly) // 기본 젤리만 더해진 시점의 잔액
            .changeType("CHARGE")
            .description(product.getProductName() + " 충전")
            .createdAt(LocalDateTime.now())
            .build();
        jellyHistoryRepository.save(chargeHistory);

        // 5-2. 보너스 젤리 적립 내역 기록 (보너스가 있을 경우에만)
        if (bonusJelly > 0) {
            JellyHistory bonusHistory = JellyHistory.builder()
                .user(user)
                .changeAmount(bonusJelly)
                .balanceAfter(balanceAfter) // 보너스까지 모두 더해진 최종 잔액
                .changeType("BONUS")
                .description(product.getBenefitDescription()) // DB에 저장된 혜택 설명 사용
                .createdAt(LocalDateTime.now().plusSeconds(1)) // 순서 보장을 위해 약간의 시간차
                .build();
            jellyHistoryRepository.save(bonusHistory);
        }

        // [수정 3] 응답 DTO(ResponseDTO) 생성 로직 변경
        return JellyChargeResponseDTO.builder()
            .purchaseId(purchase.getJellyPurchaseId())
            .productName(product.getProductName())
            .chargedJelly(totalAddedJelly) // 기본+보너스를 합산한 총 충전 젤리
            .balanceAfter(balanceAfter)     // 최종 잔액
            .build();
    }
}