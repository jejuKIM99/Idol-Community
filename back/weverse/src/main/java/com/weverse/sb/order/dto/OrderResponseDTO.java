package com.weverse.sb.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.weverse.sb.order.entity.Order;

import lombok.Getter;

@Getter
public class OrderResponseDTO {

	private Long orderId;
    private String orderNumber;
    private LocalDateTime orderedAt;
    private String status;
    private long totalPrice;
    private List<OrderItemResponseDTO> orderItems;

    // private 생성자: 정적 팩토리 메서드 사용을 권장하기 위함
    private OrderResponseDTO(Order order) {
        this.orderId = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.orderedAt = order.getOrderedAt();
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        // 주문에 포함된 모든 OrderItem들을 OrderItemResponseDTO로 변환
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Orders 엔티티를 OrderResponseDTO로 변환하는 정적 팩토리 메서드
     * @param order Order 엔티티 객체
     * @return 변환된 DTO 객체
     */
    public static OrderResponseDTO from(Order order) {
        return new OrderResponseDTO(order);
    }
	
}
