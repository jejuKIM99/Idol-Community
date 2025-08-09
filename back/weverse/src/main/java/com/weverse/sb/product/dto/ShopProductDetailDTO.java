package com.weverse.sb.product.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopProductDetailDTO {

    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stockQty;
    private Long groupId;
    private Long artistId;

    private CategoryDTO category;
    private List<ImageDTO> images;
    private List<OptionDTO> options;
    private NoticeDTO notice;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDTO {
        private Long categoryId;
        private String categoryName;
        private ParentCategoryDTO parentCategory;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ParentCategoryDTO {
            private Long categoryId;
            private String categoryName;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageDTO {
        private String imageUrl;
        private String imageType;
        private Integer sortOrder;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionDTO {
        private String optionName;
        private Integer stockQty;
        private BigDecimal additionalPrice;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoticeDTO {
        private String productName;
        private String material;
        private String sizeInfo;
        private String components;
        private String manufacturer;
        private String originCountry;
        private String manufactureDate;
        private String certificationInfo;
        private String careInstructions;
        private String qualityGuarantee;
        private String sellerName;
        private String sellerLicenseNo;
        private String phone;
        private String email;
        private String address;
    }
}

