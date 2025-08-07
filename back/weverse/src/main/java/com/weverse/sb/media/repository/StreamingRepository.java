package com.weverse.sb.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.media.entity.Streaming;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long>{
	List<Streaming> findByOwner_Group_GroupId(Long groupId);

}
