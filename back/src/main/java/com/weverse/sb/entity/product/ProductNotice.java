package com.weverse.sb.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_NOTICE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductNotice {

    @Id
    @Column(name = "product_notice_id")
    private Long productNoticeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "material")
    private String material;

    @Column(name = "size_info")
    private String sizeInfo;

    @Column(name = "components")
    private String components;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "origin_country")
    private String originCountry;

    @Column(name = "manufacture_date")
    private String manufactureDate;

    @Column(name = "certification_info")
    private String certificationInfo;

    @Column(name = "care_instructions", length = 1000)
    private String careInstructions;

    @Column(name = "quality_guarantee", length = 500)
    private String qualityGuarantee;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_license_no")
    private String sellerLicenseNo;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address", length = 300)
    private String address;
}

