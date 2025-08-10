package com.weverse.sb.media.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.media.dto.LiveChatMessageDTO;
import com.weverse.sb.media.dto.MediaChatMessageDTO;
import com.weverse.sb.media.dto.StreamingDTO;
import com.weverse.sb.media.entity.LiveChatMessage;
import com.weverse.sb.media.entity.MediaChatMessage;
import com.weverse.sb.media.entity.Streaming;
import com.weverse.sb.media.entity.UploadedVideo;
import com.weverse.sb.media.repository.LiveChatMessageRepository;
import com.weverse.sb.media.repository.MediaChatMessageRepository;
import com.weverse.sb.media.repository.StreamingRepository;
import com.weverse.sb.media.repository.UploadedVideoRepository;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.repository.UserRepository;

@Service
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	private StreamingRepository streamingRepository;
	
	@Autowired
	private UploadedVideoRepository uploadedVideoRepository;
	
	@Autowired
	private LiveChatMessageRepository liveChatMessageRepository;
	
	@Autowired
	private MediaChatMessageRepository mediaChatMessageRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<StreamingDTO> getStreamingDTOList(Long groupId, Long userId) {
		List<Streaming> streamList = streamingRepository.findByGroup_GroupId(groupId);
		List<StreamingDTO> dtoList = new ArrayList<>();

		for (Streaming stream : streamList) {
		    
		    Long streamer = (stream.getOwner() != null) ? stream.getOwner().getArtistId() : null;

		    StreamingDTO dto = StreamingDTO.builder()
		            .owner(streamer)
		            .streamer(streamer)
		            .title(stream.getTitle())
		            .thumbnail(stream.getThumbnail())
		            .createdAt(stream.getCreatedAt())
		            .scheduledAt(stream.getScheduledAt())
		            .groupId(groupId)
		            .groupName(stream.getGroup().getGroupName())
		            .build();

		    dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public void inputChatMessage(LiveChatMessageDTO chatMessage) {
		
		User user = userRepository.findById(chatMessage.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));
		Streaming streaming = streamingRepository.findById(chatMessage.getStreamingId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid streaming ID"));
       
		LiveChatMessage message = LiveChatMessage.builder()
                .sentAt(chatMessage.getSentAt())
                .streaming(streaming)
                .user(user)
                .nickname(chatMessage.getNickname())
                .content(chatMessage.getContent())
                .build();

		liveChatMessageRepository.save(message);
		
	}

	@Override
	public void inputMediaChatMessage(MediaChatMessageDTO mediaMessage) {
		
		User user = userRepository.findById(mediaMessage.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));
		UploadedVideo video = uploadedVideoRepository.findById(mediaMessage.getMediaId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid video ID"));
       
		MediaChatMessage message = MediaChatMessage.builder()
                .sentAt(mediaMessage.getSentAt())
                .uploadedVideo(video)
                .user(user)
                .nickname(mediaMessage.getNickname())
                .content(mediaMessage.getContent())
                .build();

		mediaChatMessageRepository.save(message);
		
	}

	@Override
	public List<MediaChatMessageDTO> getMessageList(Long mediaId) {
		List<MediaChatMessage> chatList = mediaChatMessageRepository.findByUploadedVideoId(mediaId);
		List<MediaChatMessageDTO> dtoList = new ArrayList<>();

		for (MediaChatMessage msg : chatList) {

			MediaChatMessageDTO dto = MediaChatMessageDTO.builder()
		            .mediaId(mediaId)
		            .userId(msg.getUser().getUserId())
		            .nickname(msg.getNickname())
		            .content(msg.getContent())
		            .sentAt(msg.getSentAt())
		            .build();

		    dtoList.add(dto);
		}

		return dtoList;
	}

}
