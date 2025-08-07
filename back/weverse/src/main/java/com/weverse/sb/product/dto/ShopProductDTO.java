package com.weverse.sb.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopProductDTO {

    private Long productId;         // 상품 ID
    private Long artistId;          // 아티스트 ID
    private Long categoryId;        // 카테고리 ID
    private String productName;     // 상품명
    private String description;     // 상품 설명
    private BigDecimal price;       // 가격
    private Integer stockQty;       // 재고 수량
    private Long groupId;           // 관계 ID (PK와 무관한 비즈니스 구분값)
}
