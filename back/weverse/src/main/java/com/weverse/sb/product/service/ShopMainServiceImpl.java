package com.weverse.sb.product.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.dto.ShopBannerDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Banner;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.BannerRepository;
import com.weverse.sb.product.dto.ShopMainResponseDTO;
import com.weverse.sb.product.dto.ShopProductDTO;
import com.weverse.sb.product.dto.ShopProductDetailDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductCategory;
import com.weverse.sb.product.entity.ProductImage;
import com.weverse.sb.product.entity.ProductNotice;
import com.weverse.sb.product.entity.ProductOption;
import com.weverse.sb.product.repository.ProductImageRepository;
import com.weverse.sb.product.repository.ProductNoticeRepository;
import com.weverse.sb.product.repository.ProductOptionRepository;
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
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductNoticeRepository productNoticeRepository;

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
    
    //5.4
    /*
    @Override
    public ShopProductDTO getProductById(Long Id) {
        return productRepository.findById(Id)
            .map(this::convertToProductDTO)
            .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
    }
    */

    private ShopBannerDTO convertToBannerDTO(Banner entity) {
        return ShopBannerDTO.builder()
                .bannerId(entity.getBannerId())                          
                .mainTitle(entity.getMainTitle())
                .artistId(entity.getArtist().getArtistId())                

                .groupId(entity.getGroupId())                  
                .subTitle(entity.getSubTitle())
                .bannerImage(entity.getBannerImage())
                .build();
    }

    private ShopArtistDTO convertToArtistDTO(Artist entity) {
        return ShopArtistDTO.builder()
                .artistId(entity.getArtistId())                           
                .groupId(entity.getGroup().getGroupId())                  

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

                .artistId(entity.getArtist().getArtistId())                
                .categoryId(entity.getCategory().getId())           
                .productName(entity.getProductName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stockQty(entity.getStockQty())
                .groupId(entity.getGroup().getGroupId())                  
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
    
    @Override
    public ShopProductDetailDTO getProductDetail(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));

        List<ProductImage> images = productImageRepository.findByProduct(product);
        List<ProductOption> options = productOptionRepository.findByProduct(product);
        ProductNotice notice = productNoticeRepository.findByProduct(product).orElse(null);

        ProductCategory category = product.getCategory();
        ProductCategory parent = category != null ? category.getParentCategory() : null;

        return ShopProductDetailDTO.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQty(product.getStockQty())
                .groupId(product.getGroup().getGroupId())
                .artistId(product.getArtist().getArtistId())

                .category(category != null ? ShopProductDetailDTO.CategoryDTO.builder()
                        .categoryId(category.getId())
                        .categoryName(category.getCategoryName())
                        .parentCategory(parent != null ? ShopProductDetailDTO.CategoryDTO.ParentCategoryDTO.builder()
                                .categoryId(parent.getId())
                                .categoryName(parent.getCategoryName())
                                .build() : null)
                        .build() : null)

                .images(images.stream().map(img -> ShopProductDetailDTO.ImageDTO.builder()
                        .imageUrl(img.getImageUrl())
                        .imageType(img.getImageType())
                        .sortOrder(img.getSortOrder())
                        .build()).toList())

                .options(options.stream().map(opt -> ShopProductDetailDTO.OptionDTO.builder()
                        .optionName(opt.getOptionName())
                        .stockQty(opt.getStockQty())
                        .additionalPrice(opt.getAdditionalPrice())
                        .build()).toList())

                .notice(notice != null ? ShopProductDetailDTO.NoticeDTO.builder()
                        .productName(notice.getProductName())
                        .material(notice.getMaterial())
                        .sizeInfo(notice.getSizeInfo())
                        .components(notice.getComponents())
                        .manufacturer(notice.getManufacturer())
                        .originCountry(notice.getOriginCountry())
                        .manufactureDate(notice.getManufactureDate())
                        .certificationInfo(notice.getCertificationInfo())
                        .careInstructions(notice.getCareInstructions())
                        .qualityGuarantee(notice.getQualityGuarantee())
                        .sellerName(notice.getSellerName())
                        .sellerLicenseNo(notice.getSellerLicenseNo())
                        .phone(notice.getPhone())
                        .email(notice.getEmail())
                        .address(notice.getAddress())
                        .build() : null)

                .build();
    }

}
