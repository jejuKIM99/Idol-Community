package com.weverse.sb.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.media.entity.UploadedVideo;

public interface UploadedVideoRepository extends JpaRepository<UploadedVideo, Long> {

}
