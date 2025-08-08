package com.weverse.sb.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

	private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stockQty;

    private List<ProductImageDTO> images;               // 이미지 리스트
    private ProductCategoryDTO category;                // 카테고리 정보
	
}
