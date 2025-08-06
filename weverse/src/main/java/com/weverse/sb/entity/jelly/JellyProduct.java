package com.weverse.sb.entity.jelly;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jelly_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JellyProduct {

    @Id
    @Column(name = "jelly_product_id")
    private Long jellyProductId;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "jelly_amount")
    private Integer jellyAmount;
}

