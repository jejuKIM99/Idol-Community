package com.weverse.sb.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.product.dto.ProductDTO;
import com.weverse.sb.product.dto.ProductOptionDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductOption;
import com.weverse.sb.product.repository.ProductOptionRepository;
import com.weverse.sb.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ArtistRepository artistRepository; 
	private final ProductOptionRepository productOptionRepository;
	private final ProductRepository productRepository;
	
	// 전체 아티스트 조회 
	@Override
	public List<ShopArtistDTO> searchArtistList() {
		
		List<Artist> artistList = this.artistRepository.findAll();
		
		return artistList.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	// 상품 상세 정보 조회
	@Override
	public ProductOptionDTO searchProductOption(Long productId) {
		
		ProductOption productOption = this.productOptionRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new RuntimeException("productOption not found"));
		
		// 엔티티값을 dto로 변환
		ProductOptionDTO dto = this.entityToDTO(productOption);
		
		return dto;
	}

	// 아티스트별 상품 목록 조회
	@Override
	public List<ProductDTO> getProductsByArtist(Long artistId) {
		List<Product> products = productRepository.findByArtistIdWithImagesAndCategory(artistId);
        return products.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
	}
	
	

}
