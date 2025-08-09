package com.weverse.sb.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.order.dto.CheckoutRequestDTO;
import com.weverse.sb.order.dto.OrderInitiateResponseDTO;
import com.weverse.sb.order.dto.OrderPreviewRequestDTO;
import com.weverse.sb.order.dto.OrderPreviewResponseDTO;
import com.weverse.sb.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/orders")
// @RequestMapping("/api/shop")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/preview")
	public ResponseEntity<OrderPreviewResponseDTO> preview(
			@RequestBody OrderPreviewRequestDTO requestDTO
			// , @AuthenticationPrincipal UserDetails userDetails
	) {
	    log.info("OrderController.preview()... POST");
		// Long currentUserId = userDetails.getUser().getUserId();
		Long currentUserId = 1L;
	    OrderPreviewResponseDTO response = orderService.calculateOrderPreview(currentUserId, requestDTO);
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/initiate")
	public ResponseEntity<OrderInitiateResponseDTO> initiate(
			@RequestBody CheckoutRequestDTO requestDTO
			// , @AuthenticationPrincipal UserDetails userDetails
	) {
		log.info("OrderController.initiate()... POST");
		// Long currentUserId = userDetails.getUser().getUserId();
		Long currentUserId = 1L;
	    OrderInitiateResponseDTO response = orderService.initiateOrder(currentUserId, requestDTO);
	    return ResponseEntity.ok(response);
	}
	
	/*
	// PG사 결제 검증 및 최종 처리 API
	@PostMapping("/validation")
	public ResponseEntity<OrderValidationResponseDTO> validate(
	        @RequestBody PaymentValidationRequestDTO requestDTO
	) {
		log.info("OrderController.validation()... POST");
		OrderValidationResponseDTO response = orderService.completeOrder(requestDTO);
	    return ResponseEntity.ok(response);
	}
	*/
	
}
