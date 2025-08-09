package com.weverse.sb.payment.service;

import org.springframework.stereotype.Component;

import com.siot.IamportRestClient.response.Payment;
import com.weverse.sb.order.service.OrderService; // 주문 서비스 주입

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderPaymentProcessor implements PaymentProcessor {

    private final OrderService orderService;

	@Override
	public void verifyAndProcess(Payment paymentData) {
		// TODO Auto-generated method stub
		
	}

}
