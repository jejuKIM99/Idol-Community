package com.weverse.sb.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.order.dto.OrderRequestDTO;
import com.weverse.sb.order.dto.OrderResponseDTO;
import com.weverse.sb.order.entity.Order;
import com.weverse.sb.order.repository.OrderRepository;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    
    @Transactional // 모든 작업이 성공하거나, 하나라도 실패하면 모두 롤백
    public OrderResponseDTO createOrder(Long userId, OrderRequestDTO requestDTO) {
        // 1. 상품 정보 조회
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        // 2. 유저 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        // 3. 총 결제 금액 계산
        int totalPrice = product.getPrice().intValueExact() * requestDTO.getQuantity();

        // 4. 잔액 확인 및 차감
        user.decreaseCash(totalPrice); // User 엔티티에 잔액 차감 로직 위임

        // 5. 주문 생성
        Order newOrder = Order.create(user, product, requestDTO.getQuantity(), totalPrice);
        
        // 6. 주문 저장
        orderRepository.save(newOrder);

        return OrderResponseDTO.from(newOrder); // DTO로 변환하여 반환
    }
	
}
