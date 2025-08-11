package com.weverse.sb.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.media.entity.UploadedVideo;

public interface UploadedVideoRepository extends JpaRepository<UploadedVideo, Long> {

	List<UploadedVideo> findByGroup_GroupId(Long groupId);

    List<UploadedVideo> findByGroupGroupIdAndMediaCategoryCategoryId(Long groupId, Long mediaCategoryId);
}
