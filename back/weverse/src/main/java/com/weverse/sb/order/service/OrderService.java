package com.weverse.sb.order.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weverse.sb.order.dto.CheckoutRequestDTO;
import com.weverse.sb.order.dto.OrderCalculationResult;
import com.weverse.sb.order.dto.OrderInitiateResponseDTO;
import com.weverse.sb.order.dto.OrderItemDTO;
import com.weverse.sb.order.dto.OrderPreviewRequestDTO;
import com.weverse.sb.order.dto.OrderPreviewResponseDTO;
import com.weverse.sb.order.dto.ShippingPolicy;
import com.weverse.sb.order.entity.Order;
import com.weverse.sb.order.entity.OrderItem;
import com.weverse.sb.order.entity.ShippingOption;
import com.weverse.sb.order.repository.OrderItemRepository;
import com.weverse.sb.order.repository.OrderRepository;
import com.weverse.sb.order.repository.ShippingOptionRepository;
import com.weverse.sb.payment.repository.PaymentRepository;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductOption;
import com.weverse.sb.product.repository.ProductOptionRepository;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.user.entity.DeliveryAddress;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.DeliveryAddressRepository;
import com.weverse.sb.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;
	private final DeliveryAddressRepository deliveryAddressRepository;
	private final ShippingOptionRepository shippingOptionRepository;
	private final ProductRepository productRepository;
	private final ProductOptionRepository productOptionRepository;
    private final PaymentRepository paymentRepository;
    
    // 주문 미리보기를 위한 서비스 메서드
    public OrderPreviewResponseDTO calculateOrderPreview(Long userId, OrderPreviewRequestDTO requestDTO) {
        // 공통 계산 로직 호출
        OrderCalculationResult result = calculateOrderDetails(userId, requestDTO.getSaleItems(), requestDTO.getDeliveryAddressId(), requestDTO.getCashToUse());

        BigDecimal subtotalPrice = result.getSubtotalPrice();
        
        // 적립 캐시 계산
        BigDecimal creditRate = new BigDecimal("0.01");
        BigDecimal calculatedCash = subtotalPrice.multiply(creditRate);
        BigDecimal creditedCash = calculatedCash.divide(BigDecimal.TEN, 0, RoundingMode.HALF_UP)
                .multiply(BigDecimal.TEN);
        
        // 계산 결과를 DTO에 담아 반환
        return OrderPreviewResponseDTO.builder()
        									.subtotalPrice(subtotalPrice)
        									.shippingFee(result.getShippingPolicy().getFee())
        									.totalPrice(result.getFinalPgAmount())
        									.creditedCash(creditedCash)
        									.build();
    }
    
    // 실제 주문 생성을 위한 서비스 메서드
    @Transactional
    public OrderInitiateResponseDTO initiateOrder(Long userId, CheckoutRequestDTO requestDTO) {
    		OrderCalculationResult result = calculateOrderDetails(userId, requestDTO.getSaleItems(), requestDTO.getDeliveryAddressId(), requestDTO.getCashToUse());
    		
    		User user = result.getUser();
    		DeliveryAddress address = result.getAddress();
    		ShippingPolicy shippingPolicy = result.getShippingPolicy();
    		
	    	// 우리 시스템의 고유 주문번호 생성
	    	String orderNumber = "order_" + System.currentTimeMillis();
	
	    	// 'PENDING' 상태의 Order 생성
	    	Order order = Order.builder()
	    			.user(user)
	    			.orderNumber(orderNumber)
	    			.recipientName(address.getRecipientName())
	    			.phoneNumber(address.getPhoneNumber())
	    			.country(address.getCountry())
	    			.city(address.getCity())
	    			.fullAddress(address.getAddress() + "\n" + address.getAddressDetail())
	    			.postalCode(address.getPostalCode())
	    			.carrierName(shippingPolicy.getCarrierName())
	    			.deliveryRequest(requestDTO.getDeliveryRequest())
	    			.subtotalPrice(result.getSubtotalPrice())
	    			.cashUsed(requestDTO.getCashToUse())
	    			.shippingFee(shippingPolicy.getFee())
	    			.totalPrice(result.getFinalPgAmount())
	    			.status("PENDING")
	    			.build();
	    	
	    	// Order와 OrderItem 관계 연결
	    	List<OrderItem> orderItems = result.getOrderItems();
	    	for (OrderItem item : orderItems) {
	    		order.addOrderItem(item);
	    	}
	    	
	    	// DB에 입력
	    	orderRepository.save(order);
	    	
	    	// 주문명
	    	String orderName = orderItems.get(0).getProduct().getProductName()
	    						+ (orderItems.size() > 1 ? " 외 " + (orderItems.size() - 1) + "건" : "");
	    	
        // 프론트에 전달
        return OrderInitiateResponseDTO.builder()
                							.orderNumber(order.getOrderNumber())
                							.orderName(orderName)
                							.amount(order.getTotalPrice())
                							.buyerEmail(user.getEmail())
                							.buyerName(user.getName())
                							.buyerTel(user.getPhoneNumber())
                							.build();
        
    }
    
    /*
    // 결제 검증 및 최종 처리를 위한 서비스 메서드
    @Transactional
    public OrderValidationResponseDTO completeOrder(PaymentValidationRequestDTO requestDTO) {
        // 'PENDING' 상태의 주문 정보 조회
        Order order = orderRepository.findByOrderNumber(requestDTO.getOrderNumber());

        // PG사 서버에 실제 결제 정보 조회 (실제 연동 시 SDK 사용)
        // IamportClient client = new IamportClient("API_KEY", "API_SECRET");
        // IamportResponse<Payment> iamportResponse = client.paymentByImpUid(requestDTO.getImp_uid());
        // BigDecimal paidAmount = iamportResponse.getResponse().getAmount();

        // (테스트용) PG사에서 받은 결제 정보가 DB의 금액과 같다고 가정
        BigDecimal paidAmount = order.getTotalPrice().subtract(order.getCashUsed());

        // 금액 검증 (PG 결제 금액이 캐시 차감 후 금액과 일치하는지)
        if (paidAmount.compareTo(order.getTotalPrice().subtract(order.getCashUsed())) != 0) {
            // 금액 불일치 시, PG사에 환불 요청 API를 호출하는 등 예외 처리 필요
            order.setStatus("FAILED");
            throw new RuntimeException("결제 금액 위변조 의심");
        }

        // --- 모든 검증 통과 ---

        // '성공 기록'인 Payment 엔티티 생성 및 저장
        Payment payment = Payment.builder()
                .user(order.getUser())
                .paymentMethod("CARD")
                .amount(paidAmount)
                .status("COMPLETED")
                .currency("KRW")
                .paymentGateway(null)
                .impUid(requestDTO.getImpUid())
                .merchantUid(requestDTO.getOrderNumber())
                .build();
        paymentRepository.save(payment);

        // 5. User의 캐시 실제로 차감
        User user = order.getUser();
        user.decreaseCash(order.getCashUsed());

        // 6. 상품 재고 차감
        for (OrderItem item : order.getOrderItems()) {
            item.getOption().decreaseStock(item.getQuantity());
        }

        // 7. Order 상태를 'PAID'로 최종 업데이트하고 Payment와 연결
        order.setStatus("PAID");
        order.setPayment(payment);
        order.setImpUid(requestDTO.getImp_uid());

        // 8. DTO로 변환하여 최종 결과 반환
        return OrderValidationResponseDTO.from(order);
    }
    */
    
    
    // 헬퍼 메서드
    
    // 계산만 담당하는 공통 헬퍼 메서드
    private OrderCalculationResult calculateOrderDetails(Long userId, List<OrderItemDTO> saleItems, Long addressId, BigDecimal cashToUse) {
	    	// 주문자 정보 조회
	    	User user = userRepository.findById(userId).orElseThrow();
	
	    	// 주문 항목(OrderItem) 리스트 생성 및 상품 소계 계산
	    	List<OrderItem> orderItems = new ArrayList<>();
	    	BigDecimal subtotalPrice = BigDecimal.ZERO;
	
	    	for (OrderItemDTO itemDTO : saleItems) {
	    		// 상품 옵션 확인
	    		ProductOption option = productOptionRepository.findById(
	    				itemDTO.getProductOptionId()).orElseThrow(()
	    						-> new RuntimeException("Option Not Found"));
	
	    		// 재고 확인
	    		if (option.getStockQty() < itemDTO.getQuantity()) {
	    			throw new RuntimeException("재고가 부족합니다.");
	    		}
	
	    		// 주문 상품 소계
	    		BigDecimal itemPrice = option.getProduct().getPrice().add(option.getAdditionalPrice());
	    		BigDecimal quantity = BigDecimal.valueOf(itemDTO.getQuantity());
	    		subtotalPrice = subtotalPrice.add(itemPrice.multiply(quantity));
	
	    		// 아직 Order가 없으므로, OrderItem을 임시로만 생성 (Order는 나중에 연결)
	    		OrderItem orderItem = OrderItem.builder()
	    				.product(option.getProduct())
	    				.option(option)
	    				.orderPrice(itemPrice)
	    				.quantity(itemDTO.getQuantity())
	    				.build();
	
	    		orderItems.add(orderItem);
	
	    	}
	    	
	    	// 배송지 정보 조회
	    	DeliveryAddress address = null;
	    	if (addressId != null) {
	    		address = deliveryAddressRepository.findById(addressId)
		    			.orElseThrow(() -> new RuntimeException("배송지를 찾을 수 없습니다."));
		} else {
			return OrderCalculationResult.builder()
											.user(user)
											.orderItems(orderItems)
											.subtotalPrice(subtotalPrice)
											.totalAmount(subtotalPrice)
											.finalPgAmount(subtotalPrice)
											.build();
		}
	    	
	    	// 배송 정책
	    	ShippingPolicy shippingPolicy = determineShippingPolicy(orderItems, address.getCountry(), subtotalPrice);
	
	    	// 최종 결제 금액 계산
	    	BigDecimal shippingFee = shippingPolicy.getFee();
	    	BigDecimal totalAmount = subtotalPrice.add(shippingFee);
	
	    	// 캐시 잔액 확인
	    	if (user.getCashBalance().compareTo(cashToUse) < 0) {
	    		throw new RuntimeException("보유 캐시가 부족합니다.");
	    	}
	
	    	// PG사로 결제할 최종 금액
	    	BigDecimal finalPgAmount = totalAmount.subtract(cashToUse);
	
	    	// 계산 결과를 담은 전용 객체 반환
	    	return OrderCalculationResult.builder()
	    									.user(user)
	    									.address(address)
	    									.orderItems(orderItems)
	    									.subtotalPrice(subtotalPrice)
	    									.shippingPolicy(shippingPolicy)
	    									.totalAmount(totalAmount)
	    									.finalPgAmount(finalPgAmount)
	    									.build();
    }
    
    // 주문 정보를 바탕으로 최종 배송 정책(비용, 택배사 등)을 결정하는 헬퍼 메서드
    private ShippingPolicy determineShippingPolicy(List<OrderItem> orderItems, String countryName, BigDecimal subtotalPrice) {

	    	// 장바구니가 비어있으면 기본 배송 정책 반환
	    	if (orderItems == null || orderItems.isEmpty()) {
	    		return new ShippingPolicy(new BigDecimal("3000"), "우체국택배", 3);
	    	}
	
	    	// 모든 상품의 배송 정책을 비교하여 '대표 배송 정책' 먼저 찾기
	    	ShippingOption representativeOption = null;
	    	BigDecimal maxFee = BigDecimal.ZERO;
	
	    	for (OrderItem item : orderItems) {
	    		Product product = item.getProduct();
	
	    		ShippingOption currentOption = shippingOptionRepository.findByProductAndCountryName(product, countryName)
	    				.orElseThrow(() -> new RuntimeException(product.getProductName() + " 상품은 " + countryName + "(으)로 배송할 수 없습니다."));
	
	    		// 가장 비싼 배송비를 가진 ShippingOption을 '대표 정책'으로 선정
	    		if (currentOption.getShippingFee().compareTo(maxFee) > 0) {
	    			maxFee = currentOption.getShippingFee();
	    			representativeOption = currentOption;
	    		}
	    	}
	
	    	// 대표 정책이 없다면 (예: 모든 상품이 배송비 0원), 첫 번째 상품의 정책을 기준으로 삼음
	    	if (representativeOption == null) {
	    		representativeOption = shippingOptionRepository.findByProductAndCountryName(orderItems.get(0).getProduct(), countryName).get();
	    	}
	
	    	// 무료배송 조건 확인
	    	BigDecimal freeShippingThreshold = new BigDecimal("50000");
	    	if (subtotalPrice.compareTo(freeShippingThreshold) >= 0) {
	    		// 무료배송이더라도, '대표 정책'의 택배사 정보는 그대로 사용하고, 배송비만 0으로 설정
	    		return new ShippingPolicy(
	    				BigDecimal.ZERO, // 배송비는 0원
	    				representativeOption.getCountryCarrier().getCarrierName(), // 택배사 정보는 유지
	    				representativeOption.getEstimatedDays()
	    				);
	    	}
	
	    	// 무료배송이 아니라면, '대표 정책'의 정보를 그대로 반환
	    	return new ShippingPolicy(
	    			representativeOption.getShippingFee(),
	    			representativeOption.getCountryCarrier().getCarrierName(),
	    			representativeOption.getEstimatedDays()
	    			);
    }
	
}
