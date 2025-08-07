package com.weverse.sb.shopTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.ShopArtistDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.product.dto.ProductDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.entity.ProductCategory;
import com.weverse.sb.product.entity.ProductImage;
import com.weverse.sb.product.repository.ProductCategoryRepository;
import com.weverse.sb.product.repository.ProductImageRepository;
import com.weverse.sb.product.repository.ProductRepository;
import com.weverse.sb.product.service.ProductService;

@SpringBootTest
public class ProductDataTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductImageRepository imageRepository;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private GroupRepository artistGroupMapRepository;

    /*
    @Test
    public void insertDummyProduct() {
    	
    	IntStream.rangeClosed(1, 3).forEach(i -> {
            ArtistGroupMap groupMap = ArtistGroupMap.builder()
                    .groupName("Group" + i)
                    .groupProfile("This is the profile of Group" + i)
                    .groupLogo("group" + i + "_logo.png")
                    .build();

            this.artistGroupMapRepository.save(groupMap);
        });
    	
    	ArtistGroupMap group = artistGroupMapRepository.findById(1L).orElseThrow();
		
		IntStream.rangeClosed(1, 5).forEach(i -> {
            Artist artist = Artist.builder()
                    .group(group)
                    .stageName("Artist " + i)
                    .email("artist" + i + "@mail.com")
                    .password("pw" + i)
                    .profileImage("artist" + i + "_profile.png")
                    .snsUrl("https://sns.com/artist" + i)
                    .birthday(LocalDateTime.of(1990 + i, 1, i, 0, 0))
                    .statusMessage("상태 메시지 " + i)
                    .dmNickname("DMNick" + i)
                    .build();

            artistRepository.save(artist);
        });

        // 1. 카테고리 생성 및 저장
        ProductCategory category = ProductCategory.builder()
                .categoryName("앨범")
                .build();
        categoryRepository.save(category);
        
        Artist artist = this.artistRepository.findById(1L).orElseThrow();

        // 2. 상품 생성
        Product product = Product.builder()
                .productName("NewJeans 앨범 ver.2025")
                .artist(artist)
                .price(BigDecimal.valueOf(19900))
                .stockQty(100)
                .description("NewJeans의 최신 앨범. 포토카드 포함")
                .category(category) // 연관관계 설정
                .build();

        productRepository.save(product); // cascade 설정이 없으면 먼저 저장해야 함

        // 3. 이미지 리스트 저장
        ProductImage img1 = ProductImage.builder()
                .product(product)
                .imageUrl("https://cdn.weverse.io/img/product1.jpg")
                .imageType("png")
                .build();

        ProductImage img2 = ProductImage.builder()
                .product(product)
                .imageUrl("https://cdn.weverse.io/img/product2.jpg")
                .imageType("png")
                .build();

        imageRepository.saveAll(Arrays.asList(img1, img2));

        System.out.println("✅ 더미 상품 등록 완료!");
    }
    */
    
    /*
    @Test
    public void artistproductTest() {
    	
    	List<ProductDTO> dto = this.productService.getProductsByArtist(1L);
    	
    	System.out.println(">> 아티스트 상품정보 : " + dto);
    	
    }
    */
    
    
    @Test
    void checkArtistList() {
       
       List<ShopArtistDTO> dto = this.productService.searchArtistList();
       
       System.out.println(">> test 아티스트 리스트: " + dto);
       
    }
    
    
    
}
