package com.weverse.sb.shop;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Banner;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.BannerRepository;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.support.entity.Notice;
import com.weverse.sb.support.repository.NoticeRepository;


@SpringBootTest
public class shopTest {

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
}
