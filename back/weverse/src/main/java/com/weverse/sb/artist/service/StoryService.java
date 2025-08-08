package com.weverse.sb.artist.service;

import com.weverse.sb.artist.dto.ArtistStoryDTO;
import com.weverse.sb.artist.dto.StoryCommentDTO;
import com.weverse.sb.artist.entity.Story;
import com.weverse.sb.artist.repository.StoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StoryService {

    private final StoryRepository storyRepository;

    public List<ArtistStoryDTO> getArtistStoryList(Long artistId) {
        // 유저 아이디는 현재 필터링엔 사용되지 않지만 향후 맞춤형 기능용으로 받아둠
        List<Story> stories = storyRepository.findByArtistArtistId(artistId);

        return stories.stream().map(story -> ArtistStoryDTO.builder()
                .storyImg(story.getStoryImg())
                .storyCreatedAt(story.getCreateAt())
                .artistName(story.getArtist().getStageName())
                .groupId(story.getGroupId())
                .comments(story.getComments().stream()
                        .map(comment -> StoryCommentDTO.builder()
                                .comment(comment.getComment())
                                .userNickname(comment.getUser().getNickname())
                                .likeCount(comment.getLikeCount())
                                .createdDate(comment.getCreateDate())
                                .build())
                        .collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList());
    }
}
