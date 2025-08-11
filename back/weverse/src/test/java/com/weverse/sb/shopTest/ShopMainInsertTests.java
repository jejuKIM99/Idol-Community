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
import com.weverse.sb.artist.entity.Banner;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.BannerRepository;
import com.weverse.sb.product.dto.ShopProductDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.product.service.ShopMainService;
import com.weverse.sb.support.entity.Notice;
import com.weverse.sb.support.repository.NoticeRepository;

@SpringBootTest
public class ShopMainInsertTests {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Test
    void insertShopMainData() {

        IntStream.rangeClosed(1, 2).forEach(i -> {
        	
            // 아티스트 
            Artist artist = Artist.builder()
                    .stageName("아티스트" + i)
                    .email("artist" + i + "@test.com")
                    .password("pass" + i)
                    .profileImage("profile" + i + ".jpg")
                    .snsUrl("https://sns" + i + ".com")
                    .birthday(LocalDateTime.of(2000, 1, 1, 0, 0))
                    .statusMessage("상태메시지" + i)
                    .dmNickname("DM닉" + i)
                    .build();
            artistRepository.save(artist);

            // 상품 
            Product product = Product.builder()
                    .productName("상품명" + i)
                    .description("설명입니다" + i)
                    .price(BigDecimal.valueOf(10000 + i * 1000))
                    .stockQty(50)
                    .artist(artist)
                    //.category(category)
                    .build();
            productRepository.save(product);

            // 배너 
            Banner banner = Banner.builder()
                    .mainTitle("메인배너 타이틀 " + i)
                    .subTitle("서브 타이틀 " + i)
                    .bannerImage("banner" + i + ".jpg")
                    .artist(artist)
                    .groupId(1L) 
                    .build();
            bannerRepository.save(banner);

            // 공지사항
            Notice notice = Notice.builder()
                    .title("공지사항 제목 " + i)
                    .content("공지 내용입니다." + i)
                    .createdAt(LocalDateTime.now())
                    .build();
            noticeRepository.save(notice);
        });
    }
    
    
    /*
    @Test
    @DisplayName("상품 엔티티로 직접 DTO 검증")
    void getProductDtoDirectly() {
        
        List<Product> products = productRepository.findAll();
        Product product = products.get(1);

        
        ShopProductDTO dto = ShopProductDTO.builder()
                .productId(product.getId())
                .artistId(product.getArtist().getArtistId())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQty(product.getStockQty())
                .groupId(product.getGroup().getGroupId())
                .build();

        System.out.println("상품 ID: " + dto.getProductId());
        System.out.println("아티스트 ID: " + dto.getArtistId());
        System.out.println("카테고리 ID: " + dto.getCategoryId());
        System.out.println("상품명: " + dto.getProductName());
        System.out.println("설명: " + dto.getDescription());
        System.out.println("가격: " + dto.getPrice());
        System.out.println("재고 수량: " + dto.getStockQty());
        System.out.println("그룹 ID: " + dto.getGroupId());
        
        // Then
        assertNotNull(dto);
        assertEquals(product.getId(), dto.getProductId());
        assertEquals(product.getProductName(), dto.getProductName());
    }*/


}
