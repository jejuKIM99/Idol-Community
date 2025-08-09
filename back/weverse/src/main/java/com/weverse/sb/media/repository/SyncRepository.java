package com.weverse.sb.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.media.entity.Sync;

@Repository
public interface SyncRepository extends JpaRepository<Sync, Long>{

}
