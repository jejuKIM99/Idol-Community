/*
package com.weverse.sb.product.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.dto.ShopProductDetailDTO;
import com.weverse.sb.product.service.ShopMainService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopMainService shopMainService;

    @GetMapping("/main")
    public ShopMainResponseDTO getShopMainData() {
        return shopMainService.getShopMainData();
    }
    
    /*
    //5.4
    @GetMapping("/products")
    public ResponseEntity<ShopProductDTO> getProductById(@RequestParam Long productId) {
        ShopProductDTO dto = shopMainService.getProductById(productId);
        return ResponseEntity.ok(dto);
    }
    
    
    @GetMapping("/products")
    public ResponseEntity<ShopProductDetailDTO> getProductDetail(@RequestParam Long productId) {
        ShopProductDetailDTO dto = shopMainService.getProductDetail(productId);
        return ResponseEntity.ok(dto);
    }
    
}
*/

