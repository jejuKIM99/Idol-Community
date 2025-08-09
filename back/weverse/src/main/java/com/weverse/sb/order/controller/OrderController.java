package com.weverse.sb.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.order.dto.OrderInitiateResponseDTO;
import com.weverse.sb.order.dto.OrderRequestDTO;
import com.weverse.sb.order.dto.OrderResponseDTO;
import com.weverse.sb.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
// @RequestMapping("/api/shop")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	// 캐시 결제 (임시 결제)
	@PostMapping("/purchaseTest")
    public ResponseEntity<OrderResponseDTO> createOrderByCash(
    		@RequestBody OrderRequestDTO requestDTO,
    		@RequestParam("userId") Long userId // @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long currentUserId = userId; // (인증된 사용자 ID)
        OrderResponseDTO response = orderService.createOrderByCash(currentUserId, requestDTO);
        return ResponseEntity.ok(response);
    }
	
	// 
	@PostMapping("/initiate")
	public ResponseEntity<OrderInitiateResponseDTO> initiateOrder(
			@RequestBody OrderRequestDTO requestDTO,
    		@RequestParam("userId") Long userId // @AuthenticationPrincipal UserDetails userDetails
	) {
	    Long currentUserId = userId;
	    OrderInitiateResponseDTO response = orderService.initiateOrder(currentUserId, requestDTO);
	    return ResponseEntity.ok(response);
	}
	
}
