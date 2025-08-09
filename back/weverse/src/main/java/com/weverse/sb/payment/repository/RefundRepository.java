package com.weverse.sb.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.payment.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    // Custom query methods (if needed) can be defined here
}