package com.weverse.sb.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weverse.sb.entity.product.UploadedVideo;

public interface UploadedVideoRepository extends JpaRepository<UploadedVideo, Long> {

}
