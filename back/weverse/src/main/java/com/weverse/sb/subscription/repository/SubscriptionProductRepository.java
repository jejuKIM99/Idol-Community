package com.weverse.sb.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.subscription.entity.SubscriptionProduct;

public interface SubscriptionProductRepository extends JpaRepository<SubscriptionProduct, Long> {

}
