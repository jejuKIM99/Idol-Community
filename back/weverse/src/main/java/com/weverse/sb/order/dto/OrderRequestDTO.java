package com.weverse.sb.order.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderRequestDTO { // 프론트에서 보낼 요청 DTO

	// 구매할 상품의 ID
    private Long productId;

    // 구매할 상품의 수량
    private Integer quantity;
    
    // (선택) 만약 상품에 색상, 사이즈 등 옵션이 있다면 옵션 ID도 받기
    private Long optionId;
    
    // 이번 결제에 사용할 캐시 금액
    private BigDecimal cashToUse;
    
    // 참고: userId는 DTO로 받지 X
    // 보안을 위해, 현재 로그인한 사용자의 정보는 스프링 시큐리티 컨텍스트에서 직접 얻어오는 것이 안전하기 때문!
	
}
