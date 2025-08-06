package com.weverse.sb.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.media.entity.Streaming;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long>{

}
