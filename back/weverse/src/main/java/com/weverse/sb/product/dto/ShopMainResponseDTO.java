package com.weverse.sb.product.dto;

import java.util.List;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.dto.ShopBannerDTO;
import com.weverse.sb.support.dto.ShopNoticeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopMainResponseDTO {
    private List<ShopBannerDTO> banners;
    private List<ShopArtistDTO> artists;
    private List<ShopProductDTO> products;
    private List<ShopNoticeDTO> notices;
}