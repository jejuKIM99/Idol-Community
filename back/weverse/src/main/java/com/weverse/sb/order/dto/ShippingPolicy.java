package com.weverse.sb.order.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShippingPolicy {

	private BigDecimal fee; // 최종 배송비
	private String carrierName; // 대표 택배사명
	private Integer estimatedDays; // 예상 배송일
	
}
