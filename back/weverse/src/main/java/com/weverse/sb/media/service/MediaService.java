package com.weverse.sb.media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.media.dto.LiveChatMessageDTO;
import com.weverse.sb.media.dto.MediaChatMessageDTO;
import com.weverse.sb.media.dto.StreamingDTO;

@Service
public interface MediaService {

	List<StreamingDTO> getStreamingDTOList(Long groupId, Long userId);

	void inputChatMessage(LiveChatMessageDTO chatMessage);

	void inputMediaChatMessage(MediaChatMessageDTO mediaMessage);

	List<MediaChatMessageDTO> getMessageList(Long mediaId);

}
