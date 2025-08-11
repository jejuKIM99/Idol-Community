package com.weverse.sb.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weverse.sb.artist.dto.ArtistDTO;
import com.weverse.sb.artist.dto.ArtistInfoResponseDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.service.ArtistService;
import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.repository.PostLikeRepository;
import com.weverse.sb.community.repository.PostRepository;
import com.weverse.sb.community.service.PostService;
import com.weverse.sb.user.repository.FavoriteRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PostController {

    private final PostRepository postRepository;
	
	@Autowired
	PostService postService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	PostLikeRepository postLikeRepository;
	
	@Autowired
	FavoriteRepository favoriteRepository;

    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
	
	// 아티스트 정보 조회 (한명)
	@GetMapping("/api/artistSNS/home/profile")
	public Artist selectArtist(@RequestParam("artistId") Long artistId) {
		return artistService.findById(artistId);
	}
	
	// 그룹 멤버 정보 조회 (멤버 전부) - 필요하시면 쓰세요
	@GetMapping("/api/artistSNS/home/profile/group")
	public ArtistInfoResponseDTO selectGroupArtist(@RequestParam Long groupId) {
	    ArtistInfoResponseDTO artistList = artistService.getArtistInfoByGroupId(groupId);

	    return artistList;
	}

	
	// 게시글 전체조회
	//  추후 principal 등으로 현재 접속중인 사용자 정보 가져올거면 
	// @RequestParam("userID") Long userID 빼도 됨
	// 일단 지금은 로그인 로직이 없는 관계로 userID 임시 추가
	@GetMapping("/api/artistSNS/home")
	public PostDTO selectPost(@RequestParam("userID") Long userID, @RequestParam("groupId") Long groupId) {
		List<PostDTO> postList = this.postService.getPostDTOList(userID, groupId);
		PostDTO dto = PostDTO.builder().postList(postList).build();

		return dto;
	}

	// 특정 아티스트 게시글 전체조회
	@GetMapping("/api/artistSNS/home/artist")
	public PostDTO filterPost(@RequestParam("artistID") Long id) {
		List<PostDTO> postList = this.postService.getFilterPostList(id);
		PostDTO dto = PostDTO.builder().postList(postList).build();

		return dto;
	} 
	
	// 포스트 좋아요 유무 확인
	@GetMapping("/api/artistSNS/post/isLike")
	public Boolean isListPost(@RequestParam("postId") Long post, @RequestParam("userId") Long userId) {
		return postLikeRepository.existsByUserUserIdAndPostId(userId, post);
	} 

	// 게시글 작성
	@PostMapping("/api/artistSNS/home/InputPost")
	public ResponseEntity<String> inputPost(@RequestParam("artistID") Long artistID,
			@RequestParam("content") String content, @RequestParam("image") MultipartFile image) {
	    try {
	        postService.inputPost(artistID, content);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	
	
	// 게시글 좋아요 하기
	@PostMapping("/api/artistSNS/home/like")
    public ResponseEntity<String> likePost(@RequestParam("postId") Long postId,
    		@RequestParam("userId") Long userId) {
        
        try {
        	postService.inputPostLike(postId, userId);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
        
    }
	
	// 게시글 좋아요 취소
	@PostMapping("/api/artistSNS/home/dislike")
    public ResponseEntity<String> unlikePost(@RequestParam("postId") Long postId, @RequestParam("userId") Long userId) {
        try {
            postService.deletePostLike(postId, userId);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
	
	// 아티스트 팔로우
	@PostMapping("/api/artistSNS/home/follow")
	public ResponseEntity<String> followArtist(@RequestParam("artistId") Long artistId, @RequestParam("userId") Long userId) {
		try {
			postService.insertFavorite(artistId, userId);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("fail: " + e.getMessage());
		}
	}
	
	
	// 아티스트 팔로우 취소
	@PostMapping("/api/artistSNS/home/unfollow")
	public ResponseEntity<String> unFollowArtist(@RequestParam("artistId") Long artistId, @RequestParam("userId") Long userId) {
		try {
			postService.deleteFavorite(artistId, userId);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("fail: " + e.getMessage());
		}
	}
	
	// 포스트 좋아요 유무 확인
	@GetMapping("/api/artistSNS/post/isFollow")
	public Boolean isPostFollow(@RequestParam("artistId") Long artistId, @RequestParam("userId") Long userId) {
		return favoriteRepository.existsByUser_UserIdAndArtist_ArtistId(userId, artistId);
	} 
	
	
}
