package com.weverse.sb.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.payment.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    // Custom query methods (if needed) can be defined here
}
