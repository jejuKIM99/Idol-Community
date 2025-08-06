package com.weverse.sb.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.order.dto.OrderRequestDTO;
import com.weverse.sb.order.dto.OrderResponseDTO;
import com.weverse.sb.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        // 현재 로그인된 사용자의 ID를 가져왔다고 가정 (원래는 시큐리티에서 유저 ID를 가져옴)
        Long currentUserId = 1L; 
        
        OrderResponseDTO response = orderService.createOrder(currentUserId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
}
