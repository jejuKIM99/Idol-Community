package com.weverse.sb.payment.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JellyChargeReadyResponseDTO {
    private String merchantUid; // 주문번호
    private String productName; // 상품명
    private BigDecimal amount;  // 결제금액
    private String buyerName;   // 구매자 이름
    private String buyerEmail;
}