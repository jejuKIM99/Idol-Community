package com.weverse.sb.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.order.entity.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    // Custom query methods (if needed) can be defined here
}
