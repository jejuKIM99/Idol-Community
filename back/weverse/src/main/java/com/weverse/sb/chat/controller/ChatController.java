package com.weverse.sb.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.media.dto.LiveChatMessageDTO;
import com.weverse.sb.media.dto.MediaChatMessageDTO;
import com.weverse.sb.media.service.MediaService;


@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	@Autowired
	MediaService mediaService;

    @PostMapping("/messages/live")
    public ResponseEntity<String> saveLiveMessage(@RequestBody LiveChatMessageDTO chatMessage) {
    	
        System.out.println("받은 getStreamingId: " + chatMessage.getStreamingId());
        System.out.println("받은 getUserId: " + chatMessage.getUserId());
        System.out.println("받은 getNickname: " + chatMessage.getNickname());
        System.out.println("받은 getContent: " + chatMessage.getContent());
        System.out.println("보낸 getSentAt: " + chatMessage.getSentAt());
        
        try {
        	mediaService.inputChatMessage(chatMessage);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		}

    }
    
    @PostMapping("/messages/media")
    public ResponseEntity<String> saveMediaMessage(@RequestBody MediaChatMessageDTO mediaMessage) {
    	
    	System.out.println("받은 getMediaId: " + mediaMessage.getMediaId());
    	System.out.println("받은 getUserId: " + mediaMessage.getUserId());
    	System.out.println("받은 getNickname: " + mediaMessage.getNickname());
    	System.out.println("받은 getContent: " + mediaMessage.getContent());
    	System.out.println("보낸 getSentAt: " + mediaMessage.getSentAt());
    	
    	try {
    		mediaService.inputMediaChatMessage(mediaMessage);
    		return ResponseEntity.ok("success");
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    	}	
    }
    
    @GetMapping("/messages/media")
    public MediaChatMessageDTO getMediaMessage(@RequestParam("id") Long mediaId) {
    	
    	List<MediaChatMessageDTO> chatList = this.mediaService.getMessageList(mediaId);
		MediaChatMessageDTO dto = MediaChatMessageDTO.builder().chatList(chatList).build();

		return dto;
		
    }
    
    
    
}
