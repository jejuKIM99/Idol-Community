package com.weverse.sb.media.repository;

import com.weverse.sb.media.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {

    /**
     * Group ID를 기준으로 Streaming 목록을 조회합니다.
     * JPA 쿼리 메서드를 사용하여 Group 엔티티의 groupId를 조건으로 사용합니다.
     * @param groupId 조회할 그룹의 ID
     * @return 해당 그룹의 스트리밍 목록
     */
	List<Streaming> findByGroup_GroupId(Long groupId);
}