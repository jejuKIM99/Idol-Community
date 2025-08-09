package com.weverse.sb.subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.subscription.entity.UserSubscription;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long>{

	// 사용자별 구독 조회
	List<UserSubscription> findByUser_UserId(Long userId);
	
	// 아티스트별 구독 조회
	List<UserSubscription> findByArtist_ArtistId(Long artistId);
}
