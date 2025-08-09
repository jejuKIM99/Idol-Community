package com.weverse.sb.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerificationRequestDTO {
	private String impUid;
    private String merchantUid;
    
    /**
     * JellyChargeRequestDTO로부터 PaymentVerificationRequestDTO를 생성하는
     * 정적 팩토리 메서드
     * @param chargeDto 변환할 원본 DTO
     * @return 변환된 PaymentVerificationRequestDTO 객체
     */
    public static PaymentVerificationRequestDTO from(JellyChargeRequestDTO chargeDto) {
        PaymentVerificationRequestDTO verificationDto = new PaymentVerificationRequestDTO();
        verificationDto.setImpUid(chargeDto.getImpUid());
        verificationDto.setMerchantUid(chargeDto.getMerchantUid());

        return verificationDto;
    }
}
