package com.weverse.sb.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.payment.entity.JellyPurchase;

@Repository
public interface JellyPurchaseRepository extends JpaRepository<JellyPurchase, Long> {

	 Optional<JellyPurchase> findByMerchantUid(String merchantUid);
    // Custom query methods (if needed) can be defined here
}
