package com.weverse.sb.repository.chatting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.chatting.ChatArchive;

@Repository
public interface ChatArchiveRepository extends JpaRepository<ChatArchive, Long>{

}
