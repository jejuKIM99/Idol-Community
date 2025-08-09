package com.weverse.sb.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "payment_method", length = 50, nullable = false)
    private String paymentMethod;

    @Column(name = "amount", precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // COMPLETED, REFUNDED 등

    @Column(name = "currency", length = 10, nullable = false)
    private String currency;

    @CreatedDate
    @Column(name = "paid_at", updatable = false)
    private LocalDateTime paidAt;

    @Column(name = "payment_gateway", length = 50)
    private String paymentGateway;
    
    // [PG사 연동] PG사 거래 ID
    @Column(name = "imp_uid", length = 255, nullable = false, unique = true)
    private String impUid;
    
    // [PG사 연동] 우리 시스템의 고유 주문 번호
    @Column(name = "merchant_uid", nullable = false, unique = true)
    private String merchantUid;
}
