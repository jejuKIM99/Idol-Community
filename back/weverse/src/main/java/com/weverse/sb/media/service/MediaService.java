package com.weverse.sb.media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.community.dto.PostDTO;
import com.weverse.sb.media.dto.StreamingDTO;

@Service
public interface MediaService {

	List<StreamingDTO> getStreamingDTOList(Long groupId, Long userId);

}
