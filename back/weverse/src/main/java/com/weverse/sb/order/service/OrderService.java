package com.weverse.sb.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.order.dto.OrderInitiateResponseDTO;
import com.weverse.sb.order.dto.OrderRequestDTO;
import com.weverse.sb.order.dto.OrderResponseDTO;
import com.weverse.sb.order.entity.Order;
import com.weverse.sb.order.repository.OrderRepository;
import com.weverse.sb.payment.entity.Payment;
import com.weverse.sb.payment.repository.PaymentRepository;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductOption;
import com.weverse.sb.product.repository.ProductOptionRepository;
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
    private final PaymentRepository paymentRepository;
    private final ProductOptionRepository productOptionRepository;
    
    @Transactional
    public OrderInitiateResponseDTO initiateOrder(Long userId, OrderRequestDTO requestDTO) {
	    	// 주문에 필요한 정보들을 모두 조회
	    	User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(requestDTO.getProductId()).orElseThrow();
        ProductOption option = null;
        
        // 상픔 옵션을 선택했다면 선택한 옵션 정보 조회
        if (requestDTO.getOptionId() != null) {
			option = productOptionRepository.findById(requestDTO.getProductId()).orElseThrow();
		}
        
        // 상품 재고가 존재하는 지 확인
        int stockQuantity = 0;
        if (option == null) { // 상품 옵션 선택 X
        	stockQuantity = product.getStockQty();
		} else { // 상품 옵션 선택 O
			stockQuantity = option.getStockQty();
		}
        if (requestDTO.getQuantity() > stockQuantity) {
        		throw new RuntimeException("상품 재고가 부족합니다.");
		}
        
        // 상품 가격 확인
        BigDecimal price = BigDecimal.ZERO;
        if (option == null) {
			price = product.getPrice();
		} else {
			price = option.getAdditionalPrice();
		}
        
        // 상품가, 배송비 등 기본 금액 계산
        int subtotalPrice = product.getPrice().intValueExact() * requestDTO.getQuantity();
        int shippingFee = calculateShippingFee(user.getCountry());
        int totalAmount = subtotalPrice + shippingFee; // 원래 내야 할 총 금액
        
        // 사용할 캐시 검증 및 실제 PG 결제 금액 계산
        int cashToUse = requestDTO.getCashToUse();
        if (user.getCashBalance() < cashToUse) {
            throw new RuntimeException("보유 캐시가 부족합니다.");
        }
        if (totalAmount < cashToUse) {
            throw new RuntimeException("사용할 캐시가 총 금액보다 많을 수 없습니다.");
        }
        int finalPgAmount = totalAmount - cashToUse; // PG사로 결제할 최종 금액

        // 우리 시스템의 고유 주문번호 생성
        String merchantUid = "order_" + System.currentTimeMillis();

        // 'PENDING' 상태의 Order 생성 (사용한 캐시 정보도 함께 기록)
        Order order = Order.builder()
                .user(user)
                .merchantUid(merchantUid)
                .status("PENDING")
                .subtotalPrice(subtotalPrice)
                .shippingFee(shippingFee)
                .cashUsed(cashToUse) // 사용한 캐시 기록
                .totalPrice((long) totalAmount) // 원래 총액 기록
                .build();
        orderRepository.save(order);

        // 프론트엔드에는 '실제 PG 결제 금액'을 전달
        return OrderInitiateResponseDTO.builder()
                .merchantUid(merchantUid)
                .productName(product.getProductName())
                .amount(finalPgAmount) // PG사 결제창에는 캐시가 차감된 금액이 표시됨
                .buyerEmail(user.getEmail())
                .buyerName(user.getName())
                .build();
    }

    @Transactional
    public OrderResponseDTO createOrderByCash(Long userId, OrderRequestDTO requestDTO) {
        // 1. 주문에 필요한 정보들을 모두 조회합니다. (User, Product, Option 등)
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(requestDTO.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        ProductOption option = productOptionRepository.findById(requestDTO.getOptionId()).orElseThrow(() -> new RuntimeException("Option not found"));

        // 2. 조회된 정보를 바탕으로 최종 결제 금액을 계산
        //    - 상품 소계 계산
        int subtotalPrice = product.getPrice().intValueExact() * requestDTO.getQuantity();
        //    - 배송비 계산 (실제로는 주소 기반으로 더 복잡한 로직이 필요)
        int shippingFee = calculateShippingFee(user.getCountry());
        //    - 최종 결제 금액 확정
        int finalAmount = subtotalPrice + shippingFee;

        // 3. User의 잔액이 충분한지 확인합니다.
        if (user.getCashBalance() < finalAmount) {
            // throw new InsufficientCashException(ErrorCode.INSUFFICIENT_CASH);
        }

        // 4. 확정된 최종 금액으로 Payment 객체를 '생성'합니다.
        Payment payment = Payment.create(user, finalAmount, "CASH");
        paymentRepository.save(payment);

        // 5. 모든 정보를 취합하여 Order 객체를 '완성'합니다. (아직 DB에 저장 X)
        Order newOrder = Order.create(user, product, option, requestDTO.getQuantity(), payment, subtotalPrice, shippingFee, finalAmount);
        
        // 6. User의 잔액을 차감합니다.
        user.decreaseCash(finalAmount);

        // 7. [최종] Order를 저장합니다. Cascade 설정 덕분에 Payment와 OrderItem도 이 시점에 함께 저장됩니다.
        orderRepository.save(newOrder);

        return OrderResponseDTO.from(newOrder);
    }
    

    // 배송비 계산 로직 예시
    private int calculateShippingFee(String country) {
        // 실제로는 국가별, 무게별 배송비 정책에 따라 계산됩니다.
        if ("South Korea".equals(country)) {
            return 3000;
        }
        return 15000; // 해외 배송비
    }
	
}
