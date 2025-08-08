package com.weverse.sb.artist.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weverse.sb.artist.dto.BoardDTO;
import com.weverse.sb.artist.entity.Board;
import com.weverse.sb.artist.entity.BoardCategory;
import com.weverse.sb.artist.entity.Group;
import com.weverse.sb.artist.repository.BoardCategoryRepository;
import com.weverse.sb.artist.repository.BoardRepository;
import com.weverse.sb.artist.repository.GroupRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	BoardCategoryRepository boardCategoryRepository;

	@Override
	public List<BoardDTO> getList(Long groupId) {
	    List<Board> boards = boardRepository.findByGroup_GroupId(groupId);
	    List<BoardDTO> boardList = new ArrayList<>();
	    
	    for (Board b : boards) {
	        BoardDTO dto = BoardDTO.builder()
	                .id(b.getId())
	                .catagoryName(b.getCategory().getTitle())
	                .groupName(b.getGroup().getGroupName())
	                .title(b.getTitle())
	                .content(b.getContent())
	                .createdAt(b.getCreatedAt())
	                .build();
	        boardList.add(dto);
	    }

	    return boardList;
	}

	@Override
	public void inputNotice(Long catagoryId, Long groupId, String title, String content) {
		
		Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));
		BoardCategory category = boardCategoryRepository.findById(groupId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));
        
        Board board = Board.builder()
        		.category(category)
                .group(group)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        

        boardRepository.save(board);
		
	}

}
