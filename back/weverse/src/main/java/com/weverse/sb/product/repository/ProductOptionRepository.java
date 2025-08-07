package com.weverse.sb.product.repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.product.entity.Product;
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
import com.weverse.sb.product.entity.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
<<<<<<< HEAD
	List<ProductOption> findByProduct(Product product);

	// 특정 상품의 옵션을 productId로 찾기(단일)
	Optional<ProductOption> findByProduct_Id(Long productId);
=======
	
	
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df
}
