package com.weverse.sb.product.repository;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
import com.weverse.sb.product.entity.ProductNotice;

@Repository
public interface ProductNoticeRepository extends JpaRepository<ProductNotice, Long> {
	
<<<<<<< HEAD
	Optional<ProductNotice> findByProduct(Product product);
=======
	
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
}
