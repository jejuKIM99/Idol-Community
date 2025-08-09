package com.weverse.sb.payment.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.weverse.sb.payment.dto.PaymentVerificationRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentVerificationService {

    private final IamportClient iamportClient; // 아임포트 클라이언트

    /**
     * PG사 결제 정보를 조회한 후, 해당 도메인의 Processor에게 검증 및 처리를 위임합니다.
     * @param request   클라이언트로부터 받은 검증 요청(imp_uid)
     * @param processor 실제 검증과 처리를 수행할 도메인별 프로세서
     */
    public void process(PaymentVerificationRequestDTO request, PaymentProcessor processor) throws IamportResponseException, IOException {
        // 1. PG사(아임포트) 서버에서 imp_uid로 결제 정보 조회
        IamportResponse<Payment> paymentResponse = iamportClient.paymentByImpUid(request.getImpUid());
        Payment paymentData = paymentResponse.getResponse();
        
        // 2. merchantUid 선택 (아임포트 잘림 이슈 대응)
        String correctMerchantUid = (request.getMerchantUid() != null) ? 
            request.getMerchantUid() : paymentData.getMerchantUid();
        
        // 3. 조회된 결제 데이터를 담당 프로세서에게 넘기고 올바른 merchantUid도 전달
        processor.verifyAndProcess(paymentData);
    }

}