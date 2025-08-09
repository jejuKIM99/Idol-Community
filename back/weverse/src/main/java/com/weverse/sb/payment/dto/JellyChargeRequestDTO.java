package com.weverse.sb.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JellyChargeRequestDTO { // 클라이언트가 젤리 충전을 요청할 때 보내는 데이터
    private Long productId;
    private String paymentMethod; // "CREDIT_CARD", "BANK_TRANSFER" 등
}