package com.weverse.sb.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weverse.sb.order.entity.ShippingOption;
import com.weverse.sb.product.entity.Product;

@Repository
public interface ShippingOptionRepository extends JpaRepository<ShippingOption, Long> {
	
	List<ShippingOption> findByProduct(Product product);
	
	// Product와 countryName으로 특정 ShippingOption을 찾는 쿼리
    @Query("SELECT so FROM ShippingOption so " +
           "JOIN FETCH so.countryCarrier cc " +
           "JOIN FETCH cc.country c " +
           "WHERE so.product = :product AND c.countryName = :countryName")
    Optional<ShippingOption> findByProductAndCountryName(@Param("product") Product product, @Param("countryName") String countryName);
	
}