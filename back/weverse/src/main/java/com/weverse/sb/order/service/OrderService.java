package com.weverse.sb.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public OrderResponseDTO createOrder(Long userId, OrderRequestDTO requestDTO) {
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

	public void setOrderStatusToPaid(com.siot.IamportRestClient.response.Payment paymentData) {
		// TODO Auto-generated method stub
		
	}
	
}
