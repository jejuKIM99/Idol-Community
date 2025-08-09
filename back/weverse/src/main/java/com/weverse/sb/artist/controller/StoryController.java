package com.weverse.sb.artist.controller;

import com.weverse.sb.artist.dto.ArtistStoryDTO;
import com.weverse.sb.artist.service.StoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
@Log4j2
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    /**
     * 아티스트 스토리 페이지 데이터 조회
     * @param userId 요청한 유저 ID (댓글 좋아요 추후에 활용 가능)
     * @param artistId 스토리 작성 아티스트 ID
     * @return 스토리 이미지, 생성일, 작성 아티스트, 그룹 ID, 댓글 리스트
     */
    @GetMapping("/artiststory")
    public List<ArtistStoryDTO> getArtistStoryList(
            @RequestParam("artistId") Long artistId
    ) {
    	log.info(">> StoryController.getArtistStoryList()...GET");
    	
        return storyService.getArtistStoryList(artistId);
    }
}
