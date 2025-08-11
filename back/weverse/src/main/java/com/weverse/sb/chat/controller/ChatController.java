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

import com.weverse.sb.chat.service.ChatService;
import com.weverse.sb.media.dto.LiveChatMessageDTO;
import com.weverse.sb.media.dto.MediaChatMessageDTO;


@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	@Autowired
	ChatService chatService;
	
	/*
	 * 
	 INSERT INTO streaming 
    (created_at, group_id, owner_artist_id, scheduled_at, streamer_artist_id, thumbnail, title, video_id)
    VALUES
    ('2025-08-09 10:00:00.000000', 1, 1, '2025-08-10 18:00:00.000000', 1, '/images/NewJeans/NewJeans_profile/NewJeans_live_01.jpg', 'First Streaming', 'https://youtu.be/wU2siJ2c5TA?si=4gGHQFcQmWfxEO--'),
    ('2025-08-09 11:00:00.000000', 1, 2, '2025-08-11 19:00:00.000000', 2, '/images/NewJeans/NewJeans_profile/NewJeans_live_02.jpg', 'Second Streaming', 'https://youtu.be/aJqS7BY5XE4?si=-C5NucKka4jJZKjZ');


    INSERT INTO uploaded_video 
    (price, artist_id, group_id, scheduled_at, uploaded_at, description, thumbnail, title, video_url)
    VALUES
    (10000.00, 1, 1, '2025-08-15 18:00:00.000000', '2025-08-09 10:00:00.000000', 'Live concert performance of Newjeans', '/images/NewJeans/NewJeans_profile/NewJeans_media_01.jpg', 'NewJeans Live Concert', 'https://youtu.be/wLApx-mWc5Y?si=GMJKox7vJz_bmh6Y'),
    (10000.00, 2, 1, NULL, '2025-08-09 11:00:00.000000', 'Behind-the-scenes footage from recent album recording', '/images/NewJeans/NewJeans_profile/NewJeans_media_02.jpg', 'NewJeans Album Recording', 'https://youtu.be/yWvzZW_g5b0?si=F-zJKDtBEoQGriNS'),
    (10000.00, 3, 1, '2025-08-20 20:00:00.000000', '2025-08-08 09:30:00.000000', 'Exclusive interview with Newjeans', '/images/NewJeans/NewJeans_profile/NewJeans_media_03.jpg', 'NewJeans Exclusive Interview', 'https://youtu.be/WOlVl32HaoY?si=slhAAUnD80tJQImh'),
    (10000.00, 4, 1, NULL, '2025-08-07 15:45:00.000000', 'Special fan meeting highlights', '/images/NewJeans/NewJeans_profile/NewJeans_media_04.jpg', 'NewJeans Fan Meeting Highlights', 'https://youtu.be/gY8_MPI3p4Q?si=hacg_d5ES8eMsVPO');

	 * 
	 */
	

    @PostMapping("/messages/live")
    public ResponseEntity<String> saveLiveMessage(@RequestBody LiveChatMessageDTO chatMessage) {
    	
        System.out.println("받은 getStreamingId: " + chatMessage.getStreamingId());
        System.out.println("받은 getUserId: " + chatMessage.getUserId());
        System.out.println("받은 getNickname: " + chatMessage.getNickname());
        System.out.println("받은 getContent: " + chatMessage.getContent());
        System.out.println("보낸 getSentAt: " + chatMessage.getSentAt());
        
        try {
        	chatService.inputChatMessage(chatMessage);
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
    		chatService.inputMediaChatMessage(mediaMessage);
    		return ResponseEntity.ok("success");
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    	}	
    }
    
    @GetMapping("/messages/media")
    public MediaChatMessageDTO getMediaMessage(@RequestParam("id") Long mediaId) {
    	
    	List<MediaChatMessageDTO> chatList = this.chatService.getMessageList(mediaId);
		MediaChatMessageDTO dto = MediaChatMessageDTO.builder().chatList(chatList).build();

		return dto;
		
    }
    
    
    
}
