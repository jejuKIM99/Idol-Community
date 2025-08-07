package com.weverse.sb.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.artist.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

	List<Board> findByGroupId(Long groupId);

}
