package com.weverse.sb.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.weverse.sb.global.entity.BaseEntity;
import com.weverse.sb.payment.entity.Payment;
import com.weverse.sb.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 우리가 관리하는 고유 주문 번호 (예: order_1660211234)
    @Column(name = "order_number", length = 50, unique = true, nullable = false)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;

    @Column(name = "recipient_name", length = 100)
    private String recipientName;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "full_address", length = 255)
    private String fullAddress;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "carrier_name", length = 100, nullable = false)
    private String carrierName;

    @Column(name = "delivery_request", length = 255)
    private String deliveryRequest;

    @Column(name = "subtotal_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal subtotalPrice;

    @Column(name = "cash_used", precision = 19, scale = 2)
    private BigDecimal cashUsed;

    @Column(name = "shipping_fee", precision = 19, scale = 2)
    private BigDecimal shippingFee;

    @Column(name = "total_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // "PENDING", "PAID", "CANCELLED" 등
    
    // [PG사 연동] PG사가 발급하는 결제 고유 ID (예: imp_123456)
    @Column(name = "imp_uid")
    private String impUid;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
