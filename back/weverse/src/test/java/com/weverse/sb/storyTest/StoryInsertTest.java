package com.weverse.sb.storyTest;


import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Story;
import com.weverse.sb.artist.entity.StoryComment;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.StoryCommentRepository;
import com.weverse.sb.artist.repository.StoryRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;
import com.weverse.sb.user.enums.Role;

@SpringBootTest
public class StoryInsertTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private StoryCommentRepository storyCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void insertDummyStoriesAndComments() {
        // 1. 이미 존재하는 아티스트와 그룹
        Artist artist = artistRepository.findByStageName("권정열")
                .orElseThrow(() -> new RuntimeException("아티스트가 존재하지 않습니다."));

        Long groupId = artist.getGroup().getGroupId();
        List<User> users = userRepository.findAll();

        // 2. 더미 유저들 가져오기 (id=1~3번 있다고 가정)
        if (users.size() < 3) {
            for (int i = users.size(); i < 3; i++) {
                User user = User.builder()
                        .email("dummy" + i + "@test.com")
                        .password("1234")
                        .name("User" + i)
                        .nickname("nick" + i)
                        .phoneNumber("010-1234-000" + i)
                        .country("KR")
                        .jellyBalance(0)
                        .cashBalance(0)
                        .isEmailVerified(false)
                        .isSmsVerified(false)
                        .createdAt(LocalDateTime.now())
                        .role(Role.ROLE_USER)
                        .build();
                userRepository.save(user);
            }

            users = userRepository.findAll(); // 유저 다시 불러오기
        }



        // 3. 3개의 스토리 insert
        for (int i = 1; i <= 3; i++) {
            Story story = Story.builder()
                    .artist(artist)
                    .storyImg("/images/10CM/stories/story" + i + ".png")
                    .groupId(groupId)
                    .createAt(LocalDateTime.now().minusDays(i))
                    .build();

            Story savedStory = storyRepository.save(story);

            // 4. 각 스토리에 댓글 2개씩 달기
            for (int j = 0; j < 2; j++) {
                StoryComment comment = StoryComment.builder()
                        .story(savedStory)
                        .user(users.get(j))
                        .comment("이 스토리 너무 좋아요! (" + i + "-" + j + ")")
                        .likeCount((long) (Math.random() * 100))
                        .createDate(LocalDateTime.now())
                        .build();
                storyCommentRepository.save(comment);
            }
        }
    }
}

