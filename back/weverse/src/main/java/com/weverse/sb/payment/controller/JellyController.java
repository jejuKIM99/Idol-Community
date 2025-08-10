package com.weverse.sb.payment.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.weverse.sb.payment.dto.JellyHistoryDTO;
import com.weverse.sb.payment.entity.JellyHistory;

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

            // JellySummaryDTO를 통해 젤리 요약 정보 조회
            JellySummaryDTO summary = jellyHistoryRepository.getJellySummary(userId);
            
            JellyUserDTO jellyUserDTO = JellyUserDTO.builder()
                    .email(userDto.getEmail())
                    .name(userDto.getName())
                    .chargedJelly(summary.getChargedJelly() != null ? summary.getChargedJelly().intValue() : 0)
                    .bonusJelly(summary.getBonusJelly() != null ? summary.getBonusJelly().intValue() : 0)
                    .totalJelly(userDto.getJellyBalance())
                    .build();
            
            return ResponseEntity.ok(jellyUserDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<JellyHistoryDTO>> getJellyHistory(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "type", defaultValue = "ALL") String type,
            @RequestParam(value = "days", defaultValue = "30") int days) {
        try {
            String email = userDetails.getUsername();
            UserDTO userDto = jwtUserService.getUserInfoByEmail(email);
            Long userId = userDto.getUserId();

            List<JellyHistory> historyList;
            
            if ("CHARGE".equals(type)) {
                historyList = jellyHistoryRepository.findByUserUserIdAndChangeTypeOrderByCreatedAtDesc(userId, "CHARGE");
            } else if ("BONUS".equals(type)) {
                historyList = jellyHistoryRepository.findByUserUserIdAndChangeTypeOrderByCreatedAtDesc(userId, "BONUS");
            } else if ("USE".equals(type)) {
                historyList = jellyHistoryRepository.findByUserUserIdAndChangeTypeOrderByCreatedAtDesc(userId, "USE");
            } else if ("CHARGE_AND_BONUS".equals(type)) {
                // 충전과 적립만 조회
                List<JellyHistory> chargeList = jellyHistoryRepository.findByUserUserIdAndChangeTypeOrderByCreatedAtDesc(userId, "CHARGE");
                List<JellyHistory> bonusList = jellyHistoryRepository.findByUserUserIdAndChangeTypeOrderByCreatedAtDesc(userId, "BONUS");
                historyList = new ArrayList<>();
                historyList.addAll(chargeList);
                historyList.addAll(bonusList);
                // 날짜순으로 정렬
                historyList.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
            } else {
                // ALL인 경우
                historyList = jellyHistoryRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
            }

            // 최근 N일 내역만 필터링
            LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
            List<JellyHistory> filteredList = historyList.stream()
                    .filter(history -> history.getCreatedAt().isAfter(cutoffDate))
                    .collect(Collectors.toList());

            List<JellyHistoryDTO> historyDTOs = filteredList.stream()
                    .map(JellyHistoryDTO::fromEntity)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(historyDTOs);
        } catch (Exception e) {
            System.err.println("Error in getJellyHistory: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
