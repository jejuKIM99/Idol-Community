package com.weverse.sb.product.dto;

import java.math.BigDecimal;

import com.weverse.sb.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionDTO {

	private String optionName;
	private Integer stockQty;
	private BigDecimal additionalPrice;
	
}
