package com.weverse.sb.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.order.entity.ShippingCountry;

@Repository
public interface ShippingCountryRepository extends JpaRepository<ShippingCountry, Long> {
	
	
}
