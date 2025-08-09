package com.weverse.sb.product.dto;

import lombok.Data;

@Data
public class BannerDTO {
    private Long bannerId;
    private String mainTitle;
    private String subTitle;
    private String bannerImage;
    private Long artistId; // artist 객체 대신 ID만 담습니다.
    private Long groupId;
}