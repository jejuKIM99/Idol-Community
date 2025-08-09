package com.weverse.sb.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.order.entity.ShippingCarrier;

@Repository
public interface ShippingCarrierRepository extends JpaRepository<ShippingCarrier, Long> {
	
	
}
