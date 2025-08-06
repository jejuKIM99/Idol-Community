package com.weverse.sb.order.entity;

import java.time.LocalDateTime;

import com.weverse.sb.payment.entity.Payment;
import com.weverse.sb.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_number", length = 50, nullable = false)
    private String orderNumber;

    @ManyToOne
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

    @Column(name = "carrier_name", length = 100, nullable = false)
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

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;
}
