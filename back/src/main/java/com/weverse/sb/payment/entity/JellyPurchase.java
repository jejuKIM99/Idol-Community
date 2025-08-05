package com.weverse.sb.payment.entity;

import java.time.LocalDateTime;

import com.weverse.sb.user.entity.User;

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

@Entity
@Table(name = "jelly_purchase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JellyPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jelly_purchase_id")
    private Long jellyPurchaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jelly_product_id", nullable = false)
    private JellyProduct jellyProduct;

    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt;
}

