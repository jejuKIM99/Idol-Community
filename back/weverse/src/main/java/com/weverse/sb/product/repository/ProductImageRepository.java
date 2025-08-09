package com.weverse.sb.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	List<ProductImage> findByProduct(Product product);
}
