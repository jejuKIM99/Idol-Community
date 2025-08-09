package com.weverse.sb.storyTest;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.dto.ArtistStoryDTO;
import com.weverse.sb.artist.dto.StoryCommentDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Story;
import com.weverse.sb.artist.entity.StoryComment;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.StoryRepository;
import com.weverse.sb.artist.service.StoryService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class StoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private StoryRepository storyRepository;
    
    @Autowired
    private StoryService storyService;

    /* [1]
    @Test
    @Transactional
    void checkStoriesAndComments() {
        // 1. 아티스트 정보 조회
        Artist artist = artistRepository.findByStageName("권정열")
                .orElseThrow(() -> new RuntimeException("아티스트가 존재하지 않습니다."));

        // 2. 해당 아티스트가 작성한 모든 스토리 조회
        List<Story> stories = storyRepository.findByArtistArtistId(artist.getArtistId());

        System.out.println("===== 권정열 스토리 및 댓글 확인 =====");

        for (Story story : stories) {
            System.out.println("스토리 이미지: " + story.getStoryImg());
            System.out.println("생성일시: " + story.getCreateAt());
            System.out.println("그룹 ID: " + story.getGroupId());

            List<StoryComment> comments = story.getComments();
            System.out.println("댓글 수: " + comments.size());

            for (StoryComment comment : comments) {
                System.out.println("  └ " + comment.getUser().getNickname() + " | " +
                        comment.getComment() + " | 좋아요: " + comment.getLikeCount());
            }
        }
    }
    */
    
    // [2]
    @Test
    void testGetArtistStoryList() {
        List<ArtistStoryDTO> storyDTOs = this.storyService.getArtistStoryList(34L);

        assertThat(storyDTOs).isNotEmpty();

        for (ArtistStoryDTO dto : storyDTOs) {
            System.out.println("Story: " + dto.getStoryImg() + ", Artist: " + dto.getArtistName());
            for (StoryCommentDTO comment : dto.getComments()) {
                System.out.println("  - Comment: " + comment.getComment() + " / 닉네임: " + comment.getUserNickname());
            }
        }
    }
}

