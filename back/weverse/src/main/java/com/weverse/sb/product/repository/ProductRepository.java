package com.weverse.sb.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// 기본 버전 (LAZY 로딩 주의)
    List<Product> findByArtist_ArtistId(Long artistId);

    // 최적화 버전 (이미지와 카테고리를 같이 가져오는 fetch join)
    @Query("SELECT DISTINCT p FROM Product p " +
           "LEFT JOIN FETCH p.images " +
           "JOIN FETCH p.category " +
           "WHERE p.artist.id = :artistId")
    List<Product> findByArtistIdWithImagesAndCategory(@Param("artistId") Long artistId);

	List<Product> findByGroup_GroupId(Long groupId);
	
}