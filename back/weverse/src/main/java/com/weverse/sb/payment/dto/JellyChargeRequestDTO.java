package com.weverse.sb.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JellyChargeRequestDTO { // 클라이언트가 젤리 충전을 요청할 때 보내는 데이터
    private Long productId;
    private String paymentMethod; // "CREDIT_CARD", "BANK_TRANSFER" 등
    
    // '결제 검증' 단계에서 사용하는 필드
    private String impUid;
    private String merchantUid; 
}