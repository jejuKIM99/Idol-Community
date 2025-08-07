package com.weverse.sb.userTest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.dto.user.UserDTO;
import com.weverse.sb.entity.user.User;
import com.weverse.sb.repository.user.UserRepository;
import com.weverse.sb.service.user.UserService;

@SpringBootTest
class UserServiceInsertTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	@Test
    void insertUsers() {
        IntStream.rangeClosed(1, 2).forEach(i -> {
            User user = User.builder()
                    .email("user" + i + "@test.com")
                    .password("1234")
                    .name("사용자" + i)
                    .nickname("nick" + i)
                    .phoneNumber("010-1234-" + String.format("%04d", i))
                    .country("KR")
                    .profileImage(null)
                    .jellyBalance(100)
                    .cashBalance(1000)
                    .isEmailVerified(true)
                    .isSmsVerified(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            this.userRepository.save(user);
        });
    }
    */
	
}
