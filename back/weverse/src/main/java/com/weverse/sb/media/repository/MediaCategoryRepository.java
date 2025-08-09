package com.weverse.sb.media.repository;

import com.weverse.sb.media.entity.MediaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaCategoryRepository extends JpaRepository<MediaCategory, Long> {
}