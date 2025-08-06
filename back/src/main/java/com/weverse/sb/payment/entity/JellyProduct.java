package com.weverse.sb.payment.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "jelly_product")
public class JellyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jelly_product_id")
    private Long jellyProductId;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "jelly_amount")
    private Integer jellyAmount;

    @Column(name = "bonusJelly")
    private Integer bonusJelly;

    @Column(name = "benefitDescription")
    private String benefitDescription;
}
