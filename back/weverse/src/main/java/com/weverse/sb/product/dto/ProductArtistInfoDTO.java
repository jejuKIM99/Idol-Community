package com.weverse.sb.product.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductArtistInfoDTO {
 private Long id;
 private String productName;
 private String description;
 private String thumbnail;
 private BigDecimal price;
 private Integer stockQty;
 private Long groupId; // group 엔티티 대신 groupId만 포함
 // 필요한 경우, Artist 정보나 ProductImage 정보도 DTO로 변환하여 포함
}