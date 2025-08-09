package com.weverse.sb.media.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.community.entity.Post;
import com.weverse.sb.media.dto.StreamingDTO;
import com.weverse.sb.media.entity.Streaming;
import com.weverse.sb.media.repository.StreamingRepository;

@Service
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	StreamingRepository streamingRepository;

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

}
