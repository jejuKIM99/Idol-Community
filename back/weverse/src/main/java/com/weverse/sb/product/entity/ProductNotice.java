package com.weverse.sb.product.entity;

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
@Table(name = "product_notice")
public class ProductNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_notice_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_name", length = 200)
    private String productName;

    @Column(name = "material", length = 100)
    private String material;

    @Column(name = "size_info", length = 200)
    private String sizeInfo;

    @Column(name = "components", length = 200)
    private String components;

    @Column(name = "manufacturer", length = 100)
    private String manufacturer;

    @Column(name = "origin_country", length = 100)
    private String originCountry;

    @Column(name = "manufacture_date", length = 20)
    private String manufactureDate;

    @Column(name = "certification_info", length = 200)
    private String certificationInfo;

    @Column(name = "care_instructions", length = 1000)
    private String careInstructions;

    @Column(name = "quality_guarantee", length = 500)
    private String qualityGuarantee;

    @Column(name = "seller_name", length = 200)
    private String sellerName;

    @Column(name = "seller_license_no", length = 100)
    private String sellerLicenseNo;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address", length = 300)
    private String address;
}
