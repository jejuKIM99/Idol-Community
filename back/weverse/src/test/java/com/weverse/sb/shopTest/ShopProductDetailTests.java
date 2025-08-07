package com.weverse.sb.shopTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.product.dto.ShopProductDetailDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductCategory;
import com.weverse.sb.product.entity.ProductImage;
import com.weverse.sb.product.entity.ProductNotice;
import com.weverse.sb.product.entity.ProductOption;
import com.weverse.sb.product.repository.ProductCategoryRepository;
import com.weverse.sb.product.repository.ProductImageRepository;
import com.weverse.sb.product.repository.ProductNoticeRepository;
import com.weverse.sb.product.repository.ProductOptionRepository;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.product.service.ShopMainService;

@SpringBootTest
public class ShopProductDetailTests {

    @Autowired private ArtistRepository artistRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ProductImageRepository productImageRepository;
    @Autowired private ProductOptionRepository productOptionRepository;
    @Autowired private ProductNoticeRepository productNoticeRepository;
    @Autowired private ProductCategoryRepository productCategoryRepository;
    @Autowired private GroupRepository groupRepository;

    @Autowired private ShopMainService shopMainService;

    @Test
    @DisplayName("상품 상세정보 저장 및 조회 테스트")
    void insertAndFetchProductDetail() {
       
       IntStream.rangeClosed(1, 3).forEach(i -> {
            Group groupMap = Group.builder()
                    .groupName("Group" + i)
                    .groupProfileImage("This is the profile of Group" + i)
                    .groupLogo("group" + i + "_logo.png")
                    .build();

            this.groupRepository.save(groupMap);
        });
       
       Group group = groupRepository.findById(1L).orElseThrow();
       
        // given
        Artist artist = artistRepository.save(
            Artist.builder()
                .stageName("testArtist")
                .email("artist@test.com")
                .password("1234")
                .profileImage("profile.jpg")
                .snsUrl("http://sns.com")
                .birthday(LocalDateTime.of(1995, 5, 5, 0, 0))
                .statusMessage("Hi")
                .group(group)
                .dmNickname("dmTest")
                .build()
        );

        ProductCategory parentCategory = productCategoryRepository.save(
            ProductCategory.builder().categoryName("상의").build()
        );

        ProductCategory childCategory = productCategoryRepository.save(
            ProductCategory.builder().categoryName("반팔").parentCategory(parentCategory).build()
        );

        
        Product product = productRepository.save(
            Product.builder()
                .productName("테스트 상품")
                .description("상세 설명")
                .price(BigDecimal.valueOf(15000))
                .stockQty(30)
                .artist(artist)
                .group(artist.getGroup())
                .category(childCategory)
                .build()
        );

        productImageRepository.saveAll(List.of(
            ProductImage.builder().product(product).imageUrl("img1.jpg").imageType("thumbnail").sortOrder(1).build(),
            ProductImage.builder().product(product).imageUrl("img2.jpg").imageType("main").sortOrder(2).build()
        ));

        productOptionRepository.saveAll(List.of(
            ProductOption.builder().product(product).optionName("옵션1").stockQty(10).additionalPrice(BigDecimal.valueOf(1000)).build(),
            ProductOption.builder().product(product).optionName("옵션2").stockQty(5).additionalPrice(BigDecimal.valueOf(2000)).build()
        ));

        productNoticeRepository.save(
            ProductNotice.builder()
                .product(product)
                .productName("테스트 상품")
                .material("면 100%")
                .sizeInfo("FREE")
                .components("옷 1개")
                .manufacturer("제조사")
                .originCountry("한국")
                .manufactureDate("2025-08")
                .certificationInfo("해당없음")
                .careInstructions("손세탁 권장")
                .qualityGuarantee("7일 이내 교환 가능")
                .sellerName("판매자명")
                .sellerLicenseNo("123-45-67890")
                .phone("010-1234-5678")
                .email("seller@test.com")
                .address("서울특별시")
                .build()
        );

        // when
        Long productId = product.getId(); // 등록된 상품 ID
        ShopProductDetailDTO detail = shopMainService.getProductDetail(1L);

        // then
        assertNotNull(detail);
        assertEquals("테스트 상품", detail.getProductName());
        assertEquals(2, detail.getImages().size());
        assertEquals(2, detail.getOptions().size());
        assertNotNull(detail.getNotice());

        // system out
        System.out.println("=== 상품 상세 정보 ===");
        System.out.println("상품 ID: " + productId);
        System.out.println("상품명: " + detail.getProductName());
        System.out.println("설명: " + detail.getDescription());
        System.out.println("가격: " + detail.getPrice());
        System.out.println("재고 수량: " + detail.getStockQty());

        System.out.println("=== 카테고리 ===");
        if (detail.getCategory() != null) {
            System.out.println("카테고리: " + detail.getCategory().getCategoryName());
            if (detail.getCategory().getParentCategory() != null) {
                System.out.println("부모 카테고리: " + detail.getCategory().getParentCategory().getCategoryName());
            }
        }

        System.out.println("=== 이미지 목록 ===");
        detail.getImages().forEach(img ->
            System.out.println("[" + img.getSortOrder() + "] " + img.getImageType() + " - " + img.getImageUrl())
        );

        System.out.println("=== 옵션 목록 ===");
        detail.getOptions().forEach(opt ->
            System.out.println(opt.getOptionName() + " / 재고: " + opt.getStockQty() + " / 추가금액: " + opt.getAdditionalPrice())
        );

        System.out.println("=== 고시 정보 ===");
        if (detail.getNotice() != null) {
            System.out.println("재질: " + detail.getNotice().getMaterial());
            System.out.println("사이즈 정보: " + detail.getNotice().getSizeInfo());
            System.out.println("구성품: " + detail.getNotice().getComponents());
            System.out.println("판매자명: " + detail.getNotice().getSellerName());
            System.out.println("연락처: " + detail.getNotice().getPhone());
        }
    }
}
