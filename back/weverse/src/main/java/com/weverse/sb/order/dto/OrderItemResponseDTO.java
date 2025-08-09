package com.weverse.sb.order.dto;

import java.math.BigDecimal;

import com.weverse.sb.order.entity.OrderItem;

import lombok.Getter;

@Getter
public class OrderItemResponseDTO {

	private String productName;
    private int quantity;
    private BigDecimal orderPrice;

    // Entity를 DTO로 변환하는 생성자
    public OrderItemResponseDTO(OrderItem orderItem) {
        this.productName = orderItem.getProduct().getProductName();
        this.quantity = orderItem.getQuantity();
        this.orderPrice = orderItem.getOrderPrice();
    }
	
}
