package com.weverse.sb.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

@Getter
@Setter // 상태 변경을 위해 Setter 추가
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jelly_purchase")
public class JellyPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jelly_purchase_id")
    private Long jellyPurchaseId; // DB 내부 관리용 주문번호

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "jelly_product_id", nullable = false)
    private JellyProduct jellyProduct;

    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt;
    
    // [PG사 연동] PG사가 발급하는 결제 고유 ID (예: imp_123456)
    @Column(name = "imp_uid")
    private String impUid;
    
    // [PG사 연동] 우리-PG사 간 통신용 고유 주문번호 (예: jelly_1660211234)
    @Column(name = "merchant_uid", unique = true, nullable = false)
    private String merchantUid;
    
    // [PG사 연동] 결제 상태 (PENDING, PAID, FAILED)
    @Column(name = "status")
    private String status;
    
    // [PG사 연동] 결제 시점의 금액을 저장할 컬럼 (스냅샷)
    // 금액을 복사해서 저장하는 이유: 시점(Point-in-Time) 가격 보존을 위해서!
    // 거래가 발생한 시점의 상품명, 가격, 수량 등의 정보를 복사해서 저장하는 것이 데이터 정합성을 지키는 표준적인 방법
    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;
    
}
