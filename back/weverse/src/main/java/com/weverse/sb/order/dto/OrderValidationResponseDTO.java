package com.weverse.sb.order.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderValidationResponseDTO { // PG사 결제 준비 결과 (결제 화면에 표시될 정보)

	// 거래 식별 정보
	private String orderNumber; // PG사에 전달할 우리측 고유 주문번호
	
	// 결제창 표시 정보
    private String orderName; // PG사 결제창에 표시될 주문명 (예: "상품명 외 2건")
    private BigDecimal amount; // PG사로 실제 결제할 최종 금액 (캐시 사용액이 차감된 금액)
    
    // 구매자 정보 (PG사 결제창 자동 채움용)
    private String buyerEmail;
    private String buyerName;
    private String buyerTel;
	
}
