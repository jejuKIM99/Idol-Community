package com.weverse.sb.payment.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.siot.IamportRestClient.response.Payment;
import com.weverse.sb.payment.entity.JellyPurchase;
import com.weverse.sb.payment.repository.JellyPurchaseRepository;

import lombok.RequiredArgsConstructor;

@Component // 이 처리기를 스프링 빈으로 등록
@RequiredArgsConstructor
public class JellyPaymentProcessor implements PaymentProcessor{
	
	private final JellyPurchaseRepository jellyPurchaseRepository;
	private final PaymentCancellationService paymentCancellationService;
	private final JellyService jellyService;

	@Override
    @Transactional
    public void verifyAndProcess(Payment paymentData) {
        // 1.  DB에서 merchant_uid로 'JellyPurchase' 정보 조회
        String merchantUid =  paymentData.getMerchantUid();
        
        JellyPurchase purchase = jellyPurchaseRepository.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 젤리 구매 요청입니다."));

        // 2. 금액 교차 검증 (DB의 주문 금액과 PG사 실제 결제 금액 비교)
        long amountFromDB = purchase.getAmount().longValue();
        long amountFromIamport = paymentData.getAmount().longValue();

        if (amountFromDB != amountFromIamport) {
            // 금액 위변조 시, 결제 취소 로직 호출
        	paymentCancellationService.cancelPayment(paymentData.getImpUid(), "젤리 구매 금액 위변조 시도");
            throw new IllegalStateException("주문 금액과 실제 결제 금액이 일치하지 않습니다.");
        }

        // 3. 멱등성 체크: 이미 처리된 결제인지 확인
        if ("PAID".equals(purchase.getStatus())) {
            return; 
        }
        
        // 4. 모든 검증 통과 시, 최종 처리
        jellyService.finalizeJellyCharge(paymentData, purchase);
        
    }

}
