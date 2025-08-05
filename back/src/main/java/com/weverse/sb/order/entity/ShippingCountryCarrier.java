package com.weverse.sb.order.entity;

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
@Table(name = "SHIPPING_COUNTRY_CARRIER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingCountryCarrier {

    @Id
    @Column(name = "country_carrier_id")
    private Long countryCarrierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private ShippingCountry country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    private ShippingCarrier carrier;

    @Column(name = "carrier_name", nullable = false)
    private String carrierName;

    @Column(name = "tracking_url")
    private String trackingUrl;
}

