package com.weverse.sb.dm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.dm.entity.AutomatedMessage;

@Repository
public interface AutomatedMessageRepository extends JpaRepository<AutomatedMessage, Long>{

}
