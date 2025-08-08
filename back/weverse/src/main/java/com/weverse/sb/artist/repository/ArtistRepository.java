package com.weverse.sb.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.artist.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
	List<Artist> findByGroup_GroupId(Long groupId);
}