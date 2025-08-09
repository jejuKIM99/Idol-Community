package com.weverse.sb.payment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.payment.dto.JellyChargeReadyResponseDTO;
import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.dto.JellyChargeResponseDTO;
import com.weverse.sb.payment.dto.JellyProductResponseDTO;
import com.weverse.sb.payment.entity.JellyHistory;
import com.weverse.sb.payment.entity.JellyProduct;
import com.weverse.sb.payment.entity.JellyPurchase;
import com.weverse.sb.payment.entity.Payment; // 우리 시스템의 Payment 엔티티
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

    /**
     * 젤리 상품 목록을 조회합니다.
     * @return 젤리 상품 DTO 목록
     */
    @Transactional(readOnly = true) // 조회 전용 트랜잭션
    public List<JellyProductResponseDTO> getJellyProductList() {
        return jellyProductRepository.findAll().stream()
            .map(JellyProductResponseDTO::fromEntity) 
            .collect(Collectors.toList());
    }

    /**
     * 클라이언트의 젤리 충전 요청을 받아, 결제를 위한 사전 준비를 수행합니다.
     * @param requestDto 상품 ID가 담긴 요청 DTO
     * @param userId     사용자 ID
     * @return PG사 결제창에 필요한 정보가 담긴 DTO
     */
    @Transactional
    public JellyChargeReadyResponseDTO prepareCharge(JellyChargeRequestDTO requestDto, Long userId) {
        // 1. 사용자 및 상품 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));
        
        JellyProduct product = jellyProductRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("젤리 상품을 찾을 수 없습니다. ID: " + requestDto.getProductId()));

        // 2. 고유 주문번호(merchant_uid) 생성 (아임포트 호환을 위해 길이 단축)
        // 형식: "jelly_타임스탬프_랜덤4자리" (e.g., jelly_1704567890_ab1c)
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000); // 10자리 타임스탬프
        String randomSuffix = UUID.randomUUID().toString().substring(0, 4); // 4글자 랜덤
        String merchantUid = "jelly_" + timestamp + "_" + randomSuffix; // 총 22글자

        // 3. '결제 대기'(PENDING) 상태의 주문(JellyPurchase) 엔티티를 생성하여 DB에 저장
        JellyPurchase purchase = JellyPurchase.builder()
                .user(user)
                .jellyProduct(product)
                .amount(product.getPrice())
                .merchantUid(merchantUid)
                .status("PENDING") // 초기 상태는 '대기'
                .paidAt(LocalDateTime.now())
                .build();
        
        jellyPurchaseRepository.save(purchase);

        // 4. 프론트엔드 결제창에 필요한 정보들을 DTO에 담아 반환
        return JellyChargeReadyResponseDTO.builder()
                .merchantUid(merchantUid)           // 방금 생성한 고유 주문번호
                .productName(product.getProductName()) // 상품명
                .amount(product.getPrice())         // 서버에서 확정한 결제 금액
                .buyerName(user.getName())      // 구매자 이름
                .buyerEmail(user.getEmail())        // 구매자 이메일
                .build();
    }

	public JellyChargeResponseDTO finalizeJellyCharge(com.siot.IamportRestClient.response.Payment paymentData, JellyPurchase purchase) {
		// 1. 사용자 및 상품 조회
		User user = purchase.getUser();
        JellyProduct product = purchase.getJellyProduct();

        // 2. 우리 시스템의 최종 Payment 엔티티 생성 및 저장
        Payment payment = Payment.builder()
            .user(user)
            .amount(paymentData.getAmount())
            .paymentMethod(paymentData.getPayMethod())
            .status("PAID") // 상태를 명확하게 'PAID'로 저장
            .impUid(paymentData.getImpUid())
            .merchantUid(purchase.getMerchantUid()) // purchase에서 merchantUid 가져오기
            .currency("KRW")
            .paidAt(LocalDateTime.now()) // 결제 완료 시점 기록
            .build();
        paymentRepository.save(payment);
        
        // 3. 기존 JellyPurchase 엔티티에 최종 결제 정보(Payment)를 연결하고 상태 변경
        purchase.completePurchase(payment); // 상태를 'PAID'로 변경하고 Payment 연결

        // 4. 사용자 젤리 잔액 업데이트 
        int balanceBefore = user.getJellyBalance();
        int totalAddedJelly = product.getJellyAmount() + product.getBonusJellyValue();
        int balanceAfter = balanceBefore + totalAddedJelly;
        user.setJellyBalance(balanceAfter);

        // 5. 젤리 변동 내역(History) 기록
        JellyHistory.logJellyTransaction(jellyHistoryRepository, user, product, balanceBefore);

        // 6. 최종 응답 DTO 생성 및 반환
        return JellyChargeResponseDTO.builder()
            .purchaseId(purchase.getJellyPurchaseId())
            .productName(product.getProductName())
            .chargedJelly(totalAddedJelly)
            .balanceAfter(balanceAfter)
            .build();
	}
}
