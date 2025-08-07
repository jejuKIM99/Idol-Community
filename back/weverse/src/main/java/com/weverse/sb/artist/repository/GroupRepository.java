package com.weverse.sb.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.weverse.sb.artist.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{

}

