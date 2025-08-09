package com.weverse.sb.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.weverse.sb.order.entity.OrderItem;
import com.weverse.sb.user.entity.DeliveryAddress;
import com.weverse.sb.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCalculationResult {

	// 주문자 정보
	private User user;
	// 배송지 정보
	private 	DeliveryAddress address;
	// 주문 상품 목록
	private List<OrderItem> orderItems;
	// 주문 상품 소계
	private BigDecimal subtotalPrice;
	// 배송 정책
	private ShippingPolicy shippingPolicy;
	// 최종 금액: 주문 상품 소계 + 배송비
	private BigDecimal totalAmount;
	// 실제 결제 금액: 최종 금액 - 캐시
	private BigDecimal finalPgAmount;
	
}
