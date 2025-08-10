package com.weverse.sb.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.payment.dto.JellyChargeReadyResponseDTO;
import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.dto.JellyProductResponseDTO;
import com.weverse.sb.payment.dto.JellyUserDTO;
import com.weverse.sb.payment.dto.JellySummaryDTO;
import com.weverse.sb.payment.dto.PaymentVerificationRequestDTO;
import com.weverse.sb.payment.service.JellyPaymentProcessor;
import com.weverse.sb.payment.service.JellyService;
import com.weverse.sb.payment.service.PaymentVerificationService;
import com.weverse.sb.payment.repository.JellyHistoryRepository;
import com.weverse.sb.user.dto.UserDTO;
import com.weverse.sb.user.service.JwtUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jelly")
@RequiredArgsConstructor
public class JellyController {

    private final JellyService jellyService;
    private final PaymentVerificationService paymentVerificationService;
    private final JellyPaymentProcessor jellyPaymentProcessor;
    private final JwtUserService jwtUserService;
    private final JellyHistoryRepository jellyHistoryRepository;

    @GetMapping("/products")
    public ResponseEntity<List<JellyProductResponseDTO>> getJellyProducts() {
        List<JellyProductResponseDTO> jellyProducts = jellyService.getJellyProductList();
        return ResponseEntity.ok(jellyProducts);
    }
    
    @PostMapping("/charge")
    public ResponseEntity<?> chargeJelly(
            @RequestBody JellyChargeRequestDTO requestDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
    	try {
            // 토큰에서 사용자 이메일 추출
            String email = userDetails.getUsername();
            
            // 이메일로 사용자 정보 조회
            // JwtUserService의 메서드 사용
            UserDTO userDto = jwtUserService.getUserInfoByEmail(email);
            Long userId = userDto.getUserId();

            // 1. 결제 준비 로직 호출
            JellyChargeReadyResponseDTO response = jellyService.prepareCharge(requestDto, userId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // 예외 종류에 따라 다른 상태 코드 반환 필요
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(
            @RequestBody PaymentVerificationRequestDTO verificationRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        try {
            // 토큰에서 사용자 이메일 추출
            String email = userDetails.getUsername();
            
            // 이메일로 사용자 정보 조회
            UserDTO userDto = jwtUserService.getUserInfoByEmail(email);
            Long userId = userDto.getUserId();

            // 결제 검증 및 처리
            paymentVerificationService.process(verificationRequest, jellyPaymentProcessor);

            // 성공 시에는 별도의 본문 없이 200 OK만 반환
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user-info")
    public ResponseEntity<JellyUserDTO> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            String email = userDetails.getUsername();
            UserDTO userDto = jwtUserService.getUserInfoByEmail(email);
            Long userId = userDto.getUserId();
            
            // JellyHistory에서 젤리 요약 정보를 한 번에 가져오기
            JellySummaryDTO jellySummary = jellyHistoryRepository.getJellySummary(userId);
            
            // 실제 보유 젤리 = 충전젤리 + 적립젤리 - 사용된 젤리
            Long actualJelly = jellySummary.getChargedJelly() + 
                              jellySummary.getBonusJelly() - 
                              jellySummary.getUsedJelly();
            
            // JellyUserDTO로 변환하여 젤리 정보 포함하여 반환
            JellyUserDTO responseDto = JellyUserDTO.builder()
                    .email(userDto.getEmail())
                    .name(userDto.getName())
                    .chargedJelly(jellySummary.getChargedJelly().intValue())
                    .bonusJelly(jellySummary.getBonusJelly().intValue())
                    .totalJelly(actualJelly.intValue())
                    .build();
            
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
