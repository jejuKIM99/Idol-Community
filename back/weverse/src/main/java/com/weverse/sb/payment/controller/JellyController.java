package com.weverse.sb.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.payment.dto.JellyChargeReadyResponseDTO;
import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.dto.JellyProductResponseDTO;
import com.weverse.sb.payment.dto.PaymentVerificationRequestDTO;
import com.weverse.sb.payment.service.JellyPaymentProcessor;
import com.weverse.sb.payment.service.JellyService;
import com.weverse.sb.payment.service.PaymentVerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jelly")
@RequiredArgsConstructor
public class JellyController {

    private final JellyService jellyService;
    private final PaymentVerificationService paymentVerificationService;
    private final JellyPaymentProcessor jellyPaymentProcessor;

    @GetMapping("/products")
    public ResponseEntity<List<JellyProductResponseDTO>> getJellyProducts() {
        List<JellyProductResponseDTO> jellyProducts = jellyService.getJellyProductList();
        return ResponseEntity.ok(jellyProducts);
    }
    
    @PostMapping("/charge")
    public ResponseEntity<?> chargeJelly(
            @RequestBody JellyChargeRequestDTO requestDto,
            @RequestParam("userId") Long userId //@AuthenticationPrincipal UserDetails userDetails
    ) {
    	try {
            // imp_uid가 없으면 '결제 준비' 단계로 판단
            if (requestDto.getImpUid() == null) {

                // 1. 결제 준비 로직 호출
                JellyChargeReadyResponseDTO response = jellyService.prepareCharge(requestDto, userId);
                return ResponseEntity.ok(response);

            } else { // imp_uid가 있으면 '결제 검증' 단계로 판단

                // 2. 결제 검증 로직 호출
            	PaymentVerificationRequestDTO verificationRequest = PaymentVerificationRequestDTO.from(requestDto);
                paymentVerificationService.process(verificationRequest, jellyPaymentProcessor);

                // 성공 시에는 별도의 본문 없이 200 OK만 반환
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            // 예외 종류에 따라 다른 상태 코드 반환 필요
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
