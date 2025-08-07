package com.weverse.sb.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryDTO {

	private Long id;	// 카테고리 ID 
    private String name;	// 카테고리명
    
}
