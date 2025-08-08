
package com.weverse.sb.artist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.ArtistInfoResponseDTO;
import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Board;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.ArtistRepository;
import com.weverse.sb.artist.repository.BoardRepository;
import com.weverse.sb.artist.repository.GroupRepository;
import com.weverse.sb.media.entity.Streaming;
import com.weverse.sb.media.entity.UploadedVideo;
import com.weverse.sb.media.repository.StreamingRepository;
import com.weverse.sb.media.repository.UploadedVideoRepository;
import com.weverse.sb.product.dto.ProductArtistInfoDTO;
import com.weverse.sb.product.entity.Product;
import com.weverse.sb.product.repository.ProductRepository;



@Service
public class ArtistService {

	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private StreamingRepository streamingRepository;

	@Autowired
	UploadedVideoRepository uploadedVideoRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BoardRepository boardRepository;

	public List<Artist> getList() {
		return artistRepository.findAll();
	}

	// 특정 아티스트 한명 조회
	public Artist findById(Long artistId) {
		Optional<Artist> artist = artistRepository.findById(artistId);

		if (artist.isPresent()) {
			Artist a = artist.get();
			Artist dto = Artist.builder()
					.artistId(a.getArtistId())
					.group(a.getGroup())
					.name(a.getName())
					.stageName(a.getStageName())
					.email(a.getEmail())
					.profileImage(a.getProfileImage())
					.build();
			return dto;
		} else {
			return null;
		}
	}	

	// 그룹 조회 서비스
	public List<Group> getGroupList() {
		return groupRepository.findAll();
	}
	
	public ArtistInfoResponseDTO getArtistInfoByGroupId(Long groupId) {
		// 그룹 조회
		Group group = groupRepository.findById(groupId)
				.orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));


		// 아티스트 조회
		List<Artist> artists = artistRepository.findByGroup_GroupId(groupId);

		ArtistInfoResponseDTO dto = ArtistInfoResponseDTO.builder().group(group).artists(artists).build();

		return dto;
	}

	//멤버조회 서비스
	public List<Artist> getArtistsByGroupId(Long groupId) {
		return artistRepository.findByGroup_GroupId(groupId);
	}

	// 라이브 조회 서비스
	public List<Streaming> getStreamingsByGroup(Long groupId) {
		return streamingRepository.findByGroup_GroupId(groupId);
	}

	// 미디어 조회 서비스
	public List<UploadedVideo> getVideosByGroupId(Long groupId) {
		return uploadedVideoRepository.findByGroup_GroupId(groupId);
	}

	// 상품 조회 서비스
	public List<ProductArtistInfoDTO> getProductsByGroupId(Long groupId) {
		List<Product> products = productRepository.findByGroup_GroupId(groupId);

		// Product 엔티티 리스트를 ProductDTO 리스트로 변환
		return products.stream()
				.map(this::convertToProductArtistInfoDTO)
				.collect(Collectors.toList());
	}

	private ProductArtistInfoDTO convertToProductArtistInfoDTO(Product product) {
		// Null 체크를 통해 안전하게 변환
		Long groupId = (product.getGroup() != null) ? product.getGroup().getGroupId() : null;

		return ProductArtistInfoDTO.builder()
				.id(product.getId())
				.productName(product.getProductName())
				.description(product.getDescription())
				.price(product.getPrice())
				.thumbnail(product.getThumbnail())
				.stockQty(product.getStockQty())
				.groupId(groupId)
				.build();
	}








	/*
    // 아티스트 sns_url 서비스
    public ArtistSnsDTO getArtistSnsById(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
            .orElseThrow(() -> new RuntimeException("해당 아티스트 없음"));

        return ArtistSnsDTO.builder()
            .artistId(artist.getArtistId())
            .snsUrl(artist.getSnsUrl())  // null이어도 그냥 반환
            .build();
    }
    // 아티스트 sns_url 최신순 3건조회
    public List<ArtistSnsDTO> getRecentArtists() {
        return artistRepository.findTop3ByOrderByArtistIdDesc().stream()
            .map(artist -> ArtistSnsDTO.builder()
                .artistId(artist.getArtistId())
                .snsUrl(artist.getSnsUrl())
                .build())
            .collect(Collectors.toList());
    }
	 */
	// 공지(보드) 최신순 3건 조회
	public List<Board> getRecentBoardsByGroupId(Long groupId) {
		return boardRepository.findTop3ByGroup_GroupIdOrderByCreatedAtDesc(groupId);
	}
}
