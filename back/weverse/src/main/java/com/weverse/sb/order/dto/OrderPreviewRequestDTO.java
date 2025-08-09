package com.weverse.sb.order.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;

@Getter
public class OrderPreviewRequestDTO {
	
	// 구매할 상품 리스트
    private List<OrderItemDTO> saleItems;
    
    // 참고: userId는 DTO로 받지 X
    // 보안을 위해, 현재 로그인한 사용자의 정보는 스프링 시큐리티 컨텍스트에서 직접 얻어오는 것이 안전하기 때문!
    
    // 배송지 정보
    private Long deliveryAddressId;
    
    // 배송 요청사항
    // private String deliveryRequest;
    
    // 이번 결제에 사용할 캐시 금액
    private BigDecimal cashToUse;
	
}
