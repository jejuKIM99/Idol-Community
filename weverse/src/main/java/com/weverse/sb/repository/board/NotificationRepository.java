package com.weverse.sb.repository.board;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.board.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Custom query methods (if needed) can be defined here
}
