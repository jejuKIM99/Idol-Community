package com.weverse.sb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.user.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long>{

}
