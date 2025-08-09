package com.weverse.sb.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
	List<ProductOption> findByProduct(Product product);

	// 특정 상품의 옵션을 productId로 찾기(단일)
	Optional<ProductOption> findByProduct_Id(Long productId);
}
