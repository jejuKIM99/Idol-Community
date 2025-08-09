package com.weverse.sb.payment.service;

import com.siot.IamportRestClient.response.Payment;

//"결제 후 처리기"라는 공통 인터페이스
public interface PaymentProcessor {
    void verifyAndProcess(Payment paymentData);


}
