package com.weverse.sb.order.dto;

import lombok.Getter;

@Getter
public class PaymentValidationRequestDTO {
	
	// 아임포트가 발급한 결제 고유 ID
	private String impUid;
	
	// 주문 번호
	private String OrderNumber;
	
}
