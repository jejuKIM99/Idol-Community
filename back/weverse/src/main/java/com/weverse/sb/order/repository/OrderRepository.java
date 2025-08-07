package com.weverse.sb.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // merchantUid로 주문 조회
    Optional<Order> findByPayment_MerchantUid(String merchantUid);
}
