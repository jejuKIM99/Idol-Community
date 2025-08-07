package com.weverse.sb.product.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
<<<<<<< HEAD
	// 기본 버전 (LAZY 로딩 주의)
    List<Product> findByArtist_Id(Long artistId);

    // 최적화 버전 (이미지와 카테고리를 같이 가져오는 fetch join)
    @Query("SELECT DISTINCT p FROM Product p " +
           "LEFT JOIN FETCH p.images " +
           "JOIN FETCH p.category " +
           "WHERE p.artist.id = :artistId")
    List<Product> findByArtistIdWithImagesAndCategory(@Param("artistId") Long artistId);
=======
	
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
	
}