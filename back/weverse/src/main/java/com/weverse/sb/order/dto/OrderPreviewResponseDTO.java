package com.weverse.sb.order.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderPreviewResponseDTO { // 계산 미리보기 결과
	
	// 상품 금액
    private BigDecimal subtotalPrice; // 상품 가격 총합

    // 할인 및 배송비
    private BigDecimal shippingFee; // 계산된 배송비

    // 최종 결제 정보
    private BigDecimal totalPrice; // 최종 결제 금액 (상품가 + 배송비 - 캐시)
    private BigDecimal creditedCash; // 이번 주문으로 적립될 예상 캐시
	
}
