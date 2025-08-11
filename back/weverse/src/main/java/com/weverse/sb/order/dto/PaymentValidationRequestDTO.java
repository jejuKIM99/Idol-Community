package com.weverse.sb.order.dto;

import lombok.Getter;

@Getter
public class PaymentValidationRequestDTO {
	
	// PortOne V2 결제 식별자 = 우리가 생성한 orderNumber와 동일하게 사용 (권장사항)
	private String paymentId;
	
	// 주문 번호
	private String OrderNumber;
	
}
