package com.weverse.sb.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.payment.dto.JellyChargeRequestDTO;
import com.weverse.sb.payment.dto.JellyChargeResponseDTO;
import com.weverse.sb.payment.dto.JellyProductResponseDTO;
import com.weverse.sb.payment.service.JellyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jelly")
@RequiredArgsConstructor
public class JellyController {

    private final JellyService jellyService;

    @GetMapping("/products")
    public ResponseEntity<List<JellyProductResponseDTO>> getJellyProducts() {
        List<JellyProductResponseDTO> jellyProducts = jellyService.getJellyProductList();
        return ResponseEntity.ok(jellyProducts);
    }
    
    @PostMapping("/charge")
    public ResponseEntity<JellyChargeResponseDTO> chargeJelly(
            @RequestBody JellyChargeRequestDTO requestDto,
            @RequestParam("userId") Long userId //@AuthenticationPrincipal UserDetails userDetails
    ) {
        JellyChargeResponseDTO response = jellyService.chargeJelly(userId, requestDto);
        return ResponseEntity.ok(response);
    }
    
    
    
}
