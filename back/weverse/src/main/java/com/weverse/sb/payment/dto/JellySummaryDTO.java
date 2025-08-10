package com.weverse.sb.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JellySummaryDTO {
    private Long chargedJelly;    // 충전젤리 총합
    private Long bonusJelly;      // 적립젤리 총합
    private Long usedJelly;       // 사용된 젤리 총합
}