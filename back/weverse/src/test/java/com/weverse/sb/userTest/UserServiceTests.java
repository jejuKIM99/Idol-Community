package com.weverse.sb.userTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weverse.sb.user.repository.UserRepository;
import com.weverse.sb.user.service.UserService;

@SpringBootTest
class UserServiceTests {

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
                    .password("1111")
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
	
	/*
	@Test
	public void getUserInfoTest() {
		UserDTO dto = this.userService.getUserInfo(1L);
		
		System.out.println(">> 1유저 정보 테스트 : " + dto);
		// >> 1유저 정보 테스트 : UserDTO(userId=1, email=user1@test.com, name=사용자1, nickname=nick1, phoneNumber=010-1234-0001, country=KR, profileImage=null, jellyBalance=100, cashBalance=1000, isEmailVerified=true, isSmsVerified=false, createdAt=2025-08-04T16:46:40.584920)
	
	}
	*/
	
	/*
	@Test
	public void updateNicknameTest() {
		String result = this.userService.updateNickname(1L, "수정닉네임");
		
		System.out.println(">> 1유저 닉네임수정 결과 테스트 : " + result);
		
	
	}
	*/
	
	@Test
	public void updatePasswordTest() {
		String result = this.userService.updatePassword(1L, "1111");
		
		System.out.println(">> 1유저 비번수정 결과 테스트 : " + result);
		
	
	}
	
	

}
