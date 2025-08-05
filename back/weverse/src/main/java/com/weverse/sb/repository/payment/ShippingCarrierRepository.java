package com.weverse.sb.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.payment.ShippingCarrier;

@Repository
public interface ShippingCarrierRepository extends JpaRepository<ShippingCarrier, Long> {
	
	
}
