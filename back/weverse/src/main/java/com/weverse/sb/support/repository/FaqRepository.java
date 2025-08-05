package com.weverse.sb.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.support.entity.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
	
	
}
