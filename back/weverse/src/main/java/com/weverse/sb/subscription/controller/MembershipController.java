package com.weverse.sb.subscription.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.subscription.entity.UserSubscription;
import com.weverse.sb.subscription.repository.UserSubscriptionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final UserSubscriptionRepository userSubscriptionRepository;

    @GetMapping("/subscriptions")
    public ResponseEntity<List<UserSubscription>> getAllSubscriptions() {
        List<UserSubscription> subscriptions = userSubscriptionRepository.findAll();
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/users/{userId}/subscriptions")
    public ResponseEntity<List<UserSubscription>> getUserSubscriptions(@PathVariable Long userId) {
        List<UserSubscription> subscriptions = userSubscriptionRepository.findByUser_UserId(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToMembership(
            @RequestParam Long userId,
            @RequestParam Long artistId,
            @RequestBody String membershipType) {
        
        // 멤버십 구독 로직 구현
        // TODO: 실제 멤버십 구독 서비스 로직 구현
        return ResponseEntity.ok("Membership subscription successful");
    }

    @GetMapping("/artists/{artistId}/memberships")
    public ResponseEntity<List<UserSubscription>> getArtistMemberships(@PathVariable Long artistId) {
        List<UserSubscription> memberships = userSubscriptionRepository.findByArtist_ArtistId(artistId);
        return ResponseEntity.ok(memberships);
    }
} 