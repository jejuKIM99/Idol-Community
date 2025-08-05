package com.weverse.sb.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.ProductNotice;

@Repository
public interface ProductNoticeRepository extends JpaRepository<ProductNotice, Long> {
	
	
}
