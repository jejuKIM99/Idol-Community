package com.weverse.sb.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.order.dto.OrderRequestDTO;
import com.weverse.sb.order.dto.OrderResponseDTO;
import com.weverse.sb.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
// @RequestMapping("/api/orders")
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/purchase")
    public ResponseEntity<OrderResponseDTO> createOrder(
    		@RequestBody OrderRequestDTO requestDTO,
    		@RequestParam("userId") Long userId // @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long currentUserId = userId;
        
        OrderResponseDTO response = orderService.createOrder(currentUserId, requestDTO);
        return ResponseEntity.ok(response);
    }
	
}
