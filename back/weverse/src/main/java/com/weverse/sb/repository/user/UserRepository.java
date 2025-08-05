package com.weverse.sb.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weverse.sb.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
