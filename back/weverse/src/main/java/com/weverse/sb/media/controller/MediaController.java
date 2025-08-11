package com.weverse.sb.media.controller;

import com.weverse.sb.media.dto.StreamingDto;
import com.weverse.sb.media.dto.VideoWithCategoryDTO;
import com.weverse.sb.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artistSNS")
@RequiredArgsConstructor
public class MediaController {

 private final MediaService mediaService;


 @GetMapping("/media")
 public ResponseEntity<List<VideoWithCategoryDTO>> getMediaVideos(@RequestParam("groupId") Long groupId) {
     List<VideoWithCategoryDTO> videosByCategory = mediaService.getVideosByGroupAndCategory(groupId);
     return ResponseEntity.ok(videosByCategory);
 }
 
 @GetMapping("/streamingAll")
 public ResponseEntity<List<StreamingDto>> getStreamingAllByGroupId(@RequestParam("groupId") Long groupId) {
     List<StreamingDto> streamingList = mediaService.getStreamingListByGroupId(groupId);
     if (streamingList.isEmpty()) {
         return ResponseEntity.noContent().build();
     }
     return ResponseEntity.ok(streamingList);
 }
 
}

