package com.weverse.sb.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.product.ProductNotice;

@Repository
public interface ProductNoticeRepository extends JpaRepository<ProductNotice, Long> {
	
	
}
