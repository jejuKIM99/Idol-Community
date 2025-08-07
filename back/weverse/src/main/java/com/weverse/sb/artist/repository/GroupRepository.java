package com.weverse.sb.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weverse.sb.artist.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}