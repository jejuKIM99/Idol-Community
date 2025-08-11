package com.weverse.sb.media.dto;

import com.weverse.sb.media.entity.UploadedVideo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoWithCategoryDTO {
 private String categoryTitle;
 private List<UploadedVideo> videos;
}