package com.weverse.sb.order.dto;

import lombok.Getter;

@Getter
public class OrderItemDTO {

	// 구매할 상품의 ID
    // private Long productId;
    
    // 구매할 상품의 옵션 ID
    private Long productOptionId;

    // 구매할 상품의 수량
    private Integer quantity;
	
}
