package com.weverse.sb.payment.dto;

import java.math.BigDecimal;

import com.weverse.sb.payment.entity.JellyProduct;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JellyProductResponseDTO { 
	private Long productId;
    private String productName;
    private BigDecimal price;
    private Integer jellyAmount;

    // JellyProduct 엔티티를 DTO로 변환하는 정적 팩토리 메서드
    public static JellyProductResponseDTO fromEntity(JellyProduct entity) {
        return JellyProductResponseDTO.builder()
            .productId(entity.getJellyProductId()) 
            .productName(entity.getProductName())
            .price(entity.getPrice())
            .jellyAmount(entity.getJellyAmount())
            .build();
        
    }
}
