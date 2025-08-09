package com.weverse.sb.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopBannerDTO {

    private Long bannerId;       // 배너 ID
    private String mainTitle;    // 메인 타이틀
    private Long artistId;       // 아티스트/그룹 ID
    private Long groupId;        // 관계 ID
    private String subTitle;     // 서브 타이틀
    private String bannerImage;  // 배너 이미지 URL
}
