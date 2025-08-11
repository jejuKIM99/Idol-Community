package com.weverse.sb.media.service;

import com.weverse.sb.media.dto.StreamingDto;
import com.weverse.sb.media.dto.VideoWithCategoryDTO;
import com.weverse.sb.media.entity.MediaCategory;
import com.weverse.sb.media.entity.Streaming;
import com.weverse.sb.media.entity.UploadedVideo;
import com.weverse.sb.media.repository.MediaCategoryRepository;
import com.weverse.sb.media.repository.StreamingRepository;
import com.weverse.sb.media.repository.UploadedVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

 private final UploadedVideoRepository uploadedVideoRepository;
 private final MediaCategoryRepository mediaCategoryRepository;
 private final StreamingRepository StreamingRepository;

 public List<VideoWithCategoryDTO> getVideosByGroupAndCategory(Long groupId) {
     // 1. 모든 미디어 카테고리 조회
     List<MediaCategory> categories = mediaCategoryRepository.findAll();

     // 2. 각 카테고리별로 영상 조회 및 DTO 생성
     return categories.stream().map(category -> {
         List<UploadedVideo> videos = uploadedVideoRepository.findByGroupGroupIdAndMediaCategoryCategoryId(groupId, category.getCategoryId());
         return VideoWithCategoryDTO.builder()
                 .categoryTitle(category.getTitle())
                 .videos(videos)
                 .build();
     }).collect(Collectors.toList());
 }
 
 public List<StreamingDto> getStreamingListByGroupId(Long groupId) {
     List<Streaming> streamingList = StreamingRepository.findByGroup_GroupId(groupId);
     return streamingList.stream()
         .map(StreamingDto::fromEntity)
         .collect(Collectors.toList());
 }
 
}
