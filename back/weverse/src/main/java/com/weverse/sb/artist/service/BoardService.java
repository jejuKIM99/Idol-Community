package com.weverse.sb.artist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.BoardDTO;


@Service
public interface BoardService {

	List<BoardDTO> getList(Long groupId);

	void inputNotice(Long catagoryId, Long groupId, String title, String content);



}
