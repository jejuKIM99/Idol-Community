package com.weverse.sb.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JellyChargeResponseDTO {
    private Long purchaseId;      // 생성된 구매 내역 ID
    private String productName;
    private Integer chargedJelly; // 충전된 젤리 양
    private Integer balanceAfter; // 충전 후 최종 젤리 잔액
}