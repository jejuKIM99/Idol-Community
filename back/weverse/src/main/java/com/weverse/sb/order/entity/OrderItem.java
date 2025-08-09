package com.weverse.sb.order.entity;

import java.math.BigDecimal;

import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductOption;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter // @Data 대신 Getter, Setter 명시
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 적용
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 적용
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 적용
    @JoinColumn(name = "option_id", nullable = false)
    private ProductOption option;

    @Column(name = "order_price", precision = 19, scale = 2, nullable = false)
    private BigDecimal orderPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    // 주문 항목 생성을 위한 정적 팩토리 메서드
    public static OrderItem create(Order order, Product product, ProductOption option, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setOption(option);
        orderItem.setQuantity(quantity);
        orderItem.setOrderPrice(product.getPrice());
        return orderItem;
    }
    
}
