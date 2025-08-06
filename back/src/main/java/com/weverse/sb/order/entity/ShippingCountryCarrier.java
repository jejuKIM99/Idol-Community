package com.weverse.sb.order.entity;

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
@Table(name = "shipping_country_carrier")
public class ShippingCountryCarrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_carrier_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private ShippingCountry country;

    @ManyToOne
    @JoinColumn(name = "carrier_id", nullable = false)
    private ShippingCarrier carrier;

    @Column(name = "carrier_name", length = 100, nullable = false)
    private String carrierName;

    @Column(name = "tracking_url", length = 300)
    private String trackingUrl;
}
