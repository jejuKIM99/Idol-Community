
<<<<<<< HEAD
package com.weverse.sb.product.controller;
=======
package com.weverse.sb.product.Controller;
>>>>>>> 2e010c7a5fb945b3bd108c29261d949027950853

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.product.dto.ProductDTO;
import com.weverse.sb.product.dto.ProductOptionDTO;
import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.dto.ShopProductDetailDTO;
import com.weverse.sb.product.service.ProductService;
import com.weverse.sb.product.service.ShopMainService;

import com.weverse.sb.user.controller.UserController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/shop")

@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final ShopMainService shopMainService;

    @GetMapping("/main")
    public ShopMainResponseDTO getShopMainData() {
        return shopMainService.getShopMainData();
    }

	
	// 아티스트별 상품 목록 조회 (artistId를 통해서 상품 리스트 담아서 보내주기)
	@GetMapping("/artists/{artistId}/products")
	public List<ProductDTO> artistProduct(Model model
			, @RequestParam("artistId") Long artistId) {
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
	public ProductOptionDTO productDetail(@RequestParam("productId") Long productId ) {
		log.info(">> ProductController.productDetail()...GET");
		
		ProductOptionDTO dto = this.productService.searchProductOption(productId);
		
		log.info(">> 가져온 특정상품 상세목록(" + productId + ") : " + dto);
		
		return dto;
	}
	
	
    
    /*
    //5.4
    @GetMapping("/products")
    public ResponseEntity<ShopProductDTO> getProductById(@RequestParam Long productId) {
        ShopProductDTO dto = shopMainService.getProductById(productId);
        return ResponseEntity.ok(dto);
    }
    */
    
    @GetMapping("/products")
    public ResponseEntity<ShopProductDetailDTO> getProductDetail(@RequestParam Long productId) {
        ShopProductDetailDTO dto = shopMainService.getProductDetail(productId);
        return ResponseEntity.ok(dto);
    }

}
