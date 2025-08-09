package com.weverse.sb.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.support.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	
	
}
