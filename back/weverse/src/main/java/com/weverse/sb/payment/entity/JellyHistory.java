package com.weverse.sb.payment.entity;

import java.time.LocalDateTime;

import com.weverse.sb.payment.repository.JellyHistoryRepository;
import com.weverse.sb.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "jelly_history")
public class JellyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jelly_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "change_amount", nullable = false)
    private Integer changeAmount;

    @Column(name = "balance_after", nullable = false)
    private Integer balanceAfter;

    @Column(name = "change_type", length = 50, nullable = false)
    private String changeType;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    
    public static void logJellyTransaction(JellyHistoryRepository jellyHistoryRepository, User user, JellyProduct product, int balanceBefore) {
    	
    	int baseJelly = product.getJellyAmount();
        int bonusJelly = product.getBonusJellyValue(); 
        
    	 // 5-1. 기본 젤리 충전 내역
        int balanceAfterBase = balanceBefore + baseJelly;
        JellyHistory chargeHistory = JellyHistory.builder()
            .user(user)
            .changeAmount(baseJelly)
            .balanceAfter(balanceAfterBase)
            .changeType("CHARGE")
            .description(product.getProductName() + " 충전")
            .build();
        jellyHistoryRepository.save(chargeHistory);

        // 5-2. 보너스 젤리 적립 내역 (보너스가 있을 경우)
        if (bonusJelly > 0) {
        	int finalBalance = balanceAfterBase + bonusJelly;
            JellyHistory bonusHistory = JellyHistory.builder()
                .user(user)
                .changeAmount(product.getBonusJelly())
                .balanceAfter(finalBalance)
                .changeType("BONUS")
                .description(product.getBenefitDescription())
                .build();
            jellyHistoryRepository.save(bonusHistory);
        }
    }
}
