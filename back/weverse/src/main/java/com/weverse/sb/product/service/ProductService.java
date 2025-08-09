package com.weverse.sb.product.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.product.dto.ProductCategoryDTO;
import com.weverse.sb.product.dto.ProductDTO;
import com.weverse.sb.product.dto.ProductImageDTO;
import com.weverse.sb.product.dto.ProductOptionDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductImage;
import com.weverse.sb.product.entity.ProductOption;

public interface ProductService {
	
	// 전체 아티스트 목록 조회
	List<ShopArtistDTO> searchArtistList();
	
	// 상품 상세 정보 조회(productOption만 들고오면 안될듯 수정 예정)
	ProductOptionDTO searchProductOption(Long productId);
	
	// 아티스트별 상품 목록 조회
	List<ProductDTO> getProductsByArtist(Long artistId);
	
	// ProductOptionDTO -> ProductOptionEntity 변환
	default ProductOptionDTO entityToDTO(ProductOption productOption) {    
		ProductOptionDTO productOptionDTO = ProductOptionDTO.builder()
				.optionName(productOption.getOptionName())
				.stockQty(productOption.getStockQty())
				.additionalPrice(productOption.getAdditionalPrice())
				.build();
		return productOptionDTO;
	}
	
	default ProductDTO entityToDTO(Product product) {

        // 이미지 리스트 DTO 변환
		List<ProductImageDTO> imageDTOs = Optional.ofNullable(product.getImages())
			    .orElse(Collections.emptyList())
			    .stream()
			    .map((ProductImage img) -> ProductImageDTO.builder()
			            .imageUrl(img.getImageUrl())
			            .imageType(img.getImageType())
			            .build())
			    .collect(Collectors.toList());

        // 카테고리 DTO 변환
        ProductCategoryDTO categoryDTO = ProductCategoryDTO.builder()
            .id(product.getCategory().getId())
            .name(product.getCategory().getCategoryName())
            .build();

        // 상품 DTO 반환
        return ProductDTO.builder()
            .productId(product.getId())
            .productName(product.getProductName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stockQty(product.getStockQty())
            .images(imageDTOs)
            .category(categoryDTO)
            .build();
    }
	
	default ShopArtistDTO entityToDTO(Artist artist) {
	    return ShopArtistDTO.builder()
	            .artistId(artist.getArtistId())
	            .groupId(artist.getGroup().getGroupId())
	            .stageName(artist.getStageName())
	            .email(artist.getEmail())
	            .password(artist.getPassword())
	            .profileImage(artist.getProfileImage())
	            .snsUrl(artist.getSnsUrl())
	            .birthday(artist.getBirthday())
	            .statusMessage(artist.getStatusMessage())
	            .dmNickname(artist.getDmNickname())
	            .build();
	}
	
}
