package com.weverse.sb.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.payment.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    // Custom query methods (if needed) can be defined here
}
