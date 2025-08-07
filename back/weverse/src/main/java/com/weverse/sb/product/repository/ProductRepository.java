package com.weverse.sb.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	// 아티스트별 상품 조회
	List<Product> findByArtist_ArtistId(Long artistId);
	
	// 상품명으로 검색 (대소문자 구분 없음)
	List<Product> findByProductNameContainingIgnoreCase(String keyword);
}