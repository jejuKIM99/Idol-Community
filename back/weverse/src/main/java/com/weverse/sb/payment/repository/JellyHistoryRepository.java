package com.weverse.sb.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weverse.sb.payment.dto.JellySummaryDTO;
import com.weverse.sb.payment.entity.JellyHistory;

@Repository
public interface JellyHistoryRepository extends JpaRepository<JellyHistory, Long> {
    
    // 사용자의 모든 젤리 거래 내역 조회
    List<JellyHistory> findByUserUserIdOrderByCreatedAtDesc(Long userId);
    
    // 사용자의 특정 타입 젤리 거래 내역 조회
    List<JellyHistory> findByUserUserIdAndChangeTypeOrderByCreatedAtDesc(Long userId, String changeType);
    
    // 충전젤리, 적립젤리, 사용된 젤리 계산    
    @Query("SELECT new com.weverse.sb.payment.dto.JellySummaryDTO(" +
           "  COALESCE(SUM(CASE WHEN h.changeType = 'CHARGE' THEN h.changeAmount ELSE 0 END), 0L), " +
           "  COALESCE(SUM(CASE WHEN h.changeType = 'BONUS' THEN h.changeAmount ELSE 0 END), 0L), " +
           "  COALESCE(SUM(CASE WHEN h.changeAmount < 0 THEN ABS(h.changeAmount) ELSE 0 END), 0L)) " +
           "FROM JellyHistory h WHERE h.user.userId = :userId")
    JellySummaryDTO getJellySummary(@Param("userId") Long userId);
}
