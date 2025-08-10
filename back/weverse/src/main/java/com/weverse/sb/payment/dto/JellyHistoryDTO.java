package com.weverse.sb.payment.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.weverse.sb.payment.entity.JellyHistory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JellyHistoryDTO {
    private Long historyId;
    private String changeType; // CHARGE, BONUS, USE
    private Integer changeAmount;
    private Integer balanceAfter;
    private String description;
    private LocalDateTime createdAt;
    private String formattedDate;
    private String formattedTime;
    
    public static JellyHistoryDTO fromEntity(JellyHistory history) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return JellyHistoryDTO.builder()
                .historyId(history.getId())
                .changeType(history.getChangeType())
                .changeAmount(history.getChangeAmount())
                .balanceAfter(history.getBalanceAfter())
                .description(history.getDescription())
                .createdAt(history.getCreatedAt())
                .formattedDate(history.getCreatedAt().format(dateFormatter))
                .formattedTime(history.getCreatedAt().format(timeFormatter))
                .build();
    }
}
