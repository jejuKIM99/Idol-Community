package com.weverse.sb.payment.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.request.CancelData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCancellationService {

    private final IamportClient iamportClient;

    public void cancelPayment(String impUid, String reason) {
        try {
            CancelData cancelData = new CancelData(impUid, true); // 전액 취소
            cancelData.setReason(reason);
            iamportClient.cancelPaymentByImpUid(cancelData);
        } catch (Exception e) {
            // 로깅 또는 커스텀 예외 처리
            System.err.println("결제 취소 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("결제 취소에 실패했습니다.", e);
        }
    }
}