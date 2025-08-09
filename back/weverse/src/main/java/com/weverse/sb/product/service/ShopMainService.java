package com.weverse.sb.product.service;



import java.util.List;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.dto.ShopBannerDTO;
import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.dto.ShopProductDTO;
import com.weverse.sb.product.dto.ShopProductDetailDTO;
import com.weverse.sb.support.dto.ShopNoticeDTO;

public interface ShopMainService {

    // 전체 배너 조회
    List<ShopBannerDTO> getMainBanners();

    // 전체 아티스트 조회
    List<ShopArtistDTO> getAllArtists();

    // 전체 상품 조회
    List<ShopProductDTO> getAllProducts();

    // 전체 샵 공지사항 조회
    List<ShopNoticeDTO> getShopNotices();
    
    ShopMainResponseDTO getShopMainData();
    
    //5.4
    //ShopProductDTO getProductById(Long productId);
    
    //5.4
    ShopProductDetailDTO getProductDetail(Long productId);
}

