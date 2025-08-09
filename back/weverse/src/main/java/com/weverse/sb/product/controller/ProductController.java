package com.weverse.sb.product.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Banner;
import com.weverse.sb.artist.repository.BannerRepository;
import com.weverse.sb.product.dto.BannerDTO;
import com.weverse.sb.product.dto.ProductDTO;
import com.weverse.sb.product.dto.ProductOptionDTO;
import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.dto.ShopProductDetailDTO;
import com.weverse.sb.product.service.ProductService;
import com.weverse.sb.product.service.ShopMainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/shop")

@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final ShopMainService shopMainService;
	private final BannerRepository bannerRepository;

    @GetMapping("/main")
    public List<BannerDTO> getShopMainBanners() {
        List<Banner> banners = bannerRepository.findAll();
        
        // 엔티티 리스트를 DTO 리스트로 변환
        return banners.stream()
                .map(banner -> {
                    BannerDTO dto = new BannerDTO();
                    dto.setBannerId(banner.getBannerId());
                    dto.setMainTitle(banner.getMainTitle());
                    dto.setSubTitle(banner.getSubTitle());
                    dto.setBannerImage(banner.getBannerImage());
                    
                    // Artist 객체가 null인지 확인하고 ID를 설정
                    Artist artist = banner.getArtist();
                    if (artist != null) {
                        dto.setArtistId(artist.getArtistId());
                    }
                    
                    dto.setGroupId(banner.getGroupId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

	
	// 아티스트별 상품 목록 조회 (artistId를 통해서 상품 리스트 담아서 보내주기)
	@GetMapping("/artists/{artistId}/products")
	public List<ProductDTO> artistProduct(@PathVariable("artistId") Long artistId) {
		log.info(">> ProductController.artistProduct()...GET");
		List<ProductDTO> products = productService.getProductsByArtist(artistId);
		
		log.info(">> 가져온 아티스트 상품리스트 목록 : " + products );
        return products;
	}
	
	// 전체 아티스트 목록 조회
	@GetMapping("/artists")
	public List<ShopArtistDTO> artistTotal() {
		log.info(">> ProductController.artistTotal()...GET");
		
		// 아티스트 stageName(활동명)을 list에 담아줌
		List<ShopArtistDTO> artistsTotal = this.productService.searchArtistList(); 
		
		log.info(">> 가져온 아티스트 목록 : " + artistsTotal );
		
		return artistsTotal;
		
	}
	
	// 상품 상세 정보 조회
	@GetMapping("/products/{productId}")
	public ResponseEntity<ShopProductDetailDTO> getProductDetail(@PathVariable("productId") Long productId) {
		log.info(">> ProductController.getProductDetail()...GET");
		ShopProductDetailDTO dto = shopMainService.getProductDetail(productId);
		log.info(">> 가져온 특정상품 상세목록(" + productId + ") : " + dto);
		return ResponseEntity.ok(dto);
	}
	
	
    
    /*
    //5.4
    @GetMapping("/products")
    public ResponseEntity<ShopProductDTO> getProductById(@RequestParam Long productId) {
        ShopProductDTO dto = shopMainService.getProductById(productId);
        return ResponseEntity.ok(dto);
    }
    */

}