package com.weverse.sb.artist.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.weverse.sb.artist.entity.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
}

