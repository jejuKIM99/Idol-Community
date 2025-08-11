package com.weverse.sb.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.media.entity.MediaChatMessage;

@Repository
public interface MediaChatMessageRepository extends JpaRepository<MediaChatMessage, Long>{

	List<MediaChatMessage> findByUploadedVideoId(Long mediaId);


}
