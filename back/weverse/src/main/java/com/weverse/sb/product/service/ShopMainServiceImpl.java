package com.weverse.sb.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.dto.ShopBannerDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Banner;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.BannerRepository;
import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.dto.ShopProductDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.support.dto.ShopNoticeDTO;
import com.weverse.sb.support.entity.Notice;
import com.weverse.sb.support.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopMainServiceImpl implements ShopMainService {

    private final BannerRepository bannerRepository;
    private final ArtistRepository artistRepository;
    private final ProductRepository productRepository;
    private final NoticeRepository noticeRepository;

    @Override
    public List<ShopBannerDTO> getMainBanners() {
        return bannerRepository.findAll()
                .stream()
                .map(this::convertToBannerDTO)
                .toList();
    }

    @Override
    public List<ShopArtistDTO> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(this::convertToArtistDTO)
                .toList();
    }

    @Override
    public List<ShopProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToProductDTO)
                .toList();
    }

    @Override
    public List<ShopNoticeDTO> getShopNotices() {
        return noticeRepository.findAll()
                .stream()
                .map(this::convertToNoticeDTO)
                .toList();
    }
    
    @Override
    public ShopMainResponseDTO getShopMainData() {
        return ShopMainResponseDTO.builder()
                .banners(getMainBanners())
                .artists(getAllArtists())
                .products(getAllProducts())
                .notices(getShopNotices())
                .build();
    }

    private ShopBannerDTO convertToBannerDTO(Banner entity) {
        return ShopBannerDTO.builder()
                .bannerId(entity.getBannerId())                          
                .mainTitle(entity.getMainTitle())
                .artistId(entity.getArtist().getId())                
                .groupId(entity.getGroupId())                  
                .subTitle(entity.getSubTitle())
                .bannerImage(entity.getBannerImage())
                .build();
    }

    private ShopArtistDTO convertToArtistDTO(Artist entity) {
        return ShopArtistDTO.builder()
                .artistId(entity.getId())                           
                .groupId(entity.getGroup().getId())                  
                .stageName(entity.getStageName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .profileImage(entity.getProfileImage())
                .snsUrl(entity.getSnsUrl())
                .birthday(entity.getBirthday())
                .statusMessage(entity.getStatusMessage())
                .dmNickname(entity.getDmNickname())
                .build();
    }

    private ShopProductDTO convertToProductDTO(Product entity) {
        return ShopProductDTO.builder()
                .productId(entity.getId())                           
                .artistId(entity.getArtist().getId())                
                .categoryId(entity.getCategory().getId())           
                .productName(entity.getProductName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stockQty(entity.getStockQty())
                .groupId(entity.getGroupId())                  
                .build();
    }

    private ShopNoticeDTO convertToNoticeDTO(Notice entity) {
        return ShopNoticeDTO.builder()
                .noticeId(entity.getId())                            
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
