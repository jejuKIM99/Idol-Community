package com.weverse.sb.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.media.dto.LiveChatMessageDTO;
import com.weverse.sb.media.entity.LiveChatMessage;
import com.weverse.sb.media.entity.MediaChatMessage;

@Repository
public interface MediaChatMessageRepository extends JpaRepository<MediaChatMessage, Long>{


}
