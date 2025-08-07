package com.weverse.sb.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.weverse.sb.artist.entity.ArtistGroupMap;

@Repository
public interface ArtistGroupMapRepository extends JpaRepository<ArtistGroupMap, Long>{
=======
import com.weverse.sb.artist.entity.Group;

@Repository
public interface ArtistGroupMapRepository extends JpaRepository<Group, Long>{
>>>>>>> fbb0660771a02fa16ada2781c92ae39376c415df

}
