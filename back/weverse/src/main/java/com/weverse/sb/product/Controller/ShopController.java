package com.weverse.sb.product.Controller;

import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.service.ShopMainService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopMainService shopMainService;

    @GetMapping("/main")
    public ShopMainResponseDTO getShopMainData() {
        return shopMainService.getShopMainData();
    }
}
