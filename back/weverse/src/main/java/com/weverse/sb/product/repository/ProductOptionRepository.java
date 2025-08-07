package com.weverse.sb.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
	
	// 상품별 옵션 조회
	List<ProductOption> findByProduct_id(Long id);
}
