package com.weverse.sb.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
	Order findByOrderNumber(String OrderNumber);
	
}
