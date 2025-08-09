package com.weverse.sb.order.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;

@Getter
public class CheckoutRequestDTO {
	
	// 구매할 상품 리스트
    private List<OrderItemDTO> saleItems;
    
    // 배송지 정보
    private Long deliveryAddressId;
    
    // 배송 요청사항
    private String deliveryRequest;
    
    // 이번 결제에 사용할 캐시 금액
    private BigDecimal cashToUse;
	
}
