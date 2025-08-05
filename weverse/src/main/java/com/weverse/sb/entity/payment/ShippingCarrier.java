package com.weverse.sb.entity.payment;

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
@Table(name = "SHIPPING_CARRIER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingCarrier {

    @Id
    @Column(name = "carrier_id")
    private Long carrierId;

    @Column(name = "carrier_name", nullable = false)
    private String carrierName;

    @Column(name = "tracking_url")
    private String trackingUrl;
}

