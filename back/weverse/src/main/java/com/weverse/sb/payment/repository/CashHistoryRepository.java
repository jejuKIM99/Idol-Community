package com.weverse.sb.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.payment.entity.CashHistory;

@Repository
public interface CashHistoryRepository extends JpaRepository<CashHistory, Long> {
    // Custom query methods (if needed) can be defined here
}
