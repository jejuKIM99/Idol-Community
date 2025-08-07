package com.weverse.sb.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductOption;
import com.weverse.sb.product.repository.ProductOptionRepository;
import com.weverse.sb.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductRepository productRepository;
	private final ProductOptionRepository productOptionRepository;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProductDetail(@PathVariable Long productId) {
		Product product = productRepository.findById(productId)
			.orElseThrow(() -> new RuntimeException("Product not found"));
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/artists/{artistId}/products")
	public ResponseEntity<List<Product>> getArtistProducts(@PathVariable Long artistId) {
		List<Product> products = productRepository.findByArtist_ArtistId(artistId);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/products/{productId}/options")
	public ResponseEntity<List<ProductOption>> getProductOptions(@PathVariable Long productId) {
		List<ProductOption> options = productOptionRepository.findByProduct_id(productId);
		return ResponseEntity.ok(options);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
		List<Product> products = productRepository.findByProductNameContainingIgnoreCase(keyword);
		return ResponseEntity.ok(products);
	}
}
