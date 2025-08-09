package com.weverse.sb.dm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.dm.entity.DmMessageArchive;

@Repository
public interface DmMessageArchiveRepository extends JpaRepository<DmMessageArchive, Long>{

}
