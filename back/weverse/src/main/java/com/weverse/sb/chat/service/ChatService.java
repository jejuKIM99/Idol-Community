package com.weverse.sb.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.media.dto.LiveChatMessageDTO;
import com.weverse.sb.media.dto.MediaChatMessageDTO;

@Service
public interface ChatService {
	
	void inputChatMessage(LiveChatMessageDTO chatMessage);

	void inputMediaChatMessage(MediaChatMessageDTO mediaMessage);

	List<MediaChatMessageDTO> getMessageList(Long mediaId);


}
