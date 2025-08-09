package com.weverse.sb.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.payment.entity.JellyHistory;

@Repository
public interface JellyHistoryRepository extends JpaRepository<JellyHistory, Long> {
    // Custom query methods (if needed) can be defined here
}
