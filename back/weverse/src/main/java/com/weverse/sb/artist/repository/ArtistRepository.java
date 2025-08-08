package com.weverse.sb.artist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.artist.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{

	// 아티스트 한 명 조회용 
	Optional<Artist> findById(Long artistId);

	// 그룹에 속한 멤버 전체 조회
	List<Artist> findByGroup_GroupId(Long groupId);
	
	Optional<Artist> findByStageName(String stageName);
}
