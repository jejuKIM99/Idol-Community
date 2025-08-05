package com.weverse.sb.entity.payment;

import java.time.LocalDateTime;

import com.weverse.sb.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_number", nullable = false, length = 50)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
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

    @Column(name = "carrier_name", nullable = false, length = 100)
    private String carrierName;

    @Column(name = "delivery_request", length = 255)
    private String deliveryRequest;

    @Column(name = "subtotal_price", nullable = false)
    private Integer subtotalPrice;

    @Column(name = "cash_used")
    private Integer cashUsed;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;
}

