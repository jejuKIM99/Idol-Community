package com.weverse.sb.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.user.ArtistGroupMap;

@Repository
public interface ArtistGroupMapRepository extends JpaRepository<ArtistGroupMap, Long>{

}
