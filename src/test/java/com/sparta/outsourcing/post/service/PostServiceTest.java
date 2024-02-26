package com.sparta.outsourcing.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

	@Mock
	PostRepository postRepository;

	@InjectMocks
	PostService postService;

	private User testUser() {
		String username = "Test";
		String password = "12345678";
		String email = "kkkkk@naver.com";
		String nickname = "nick";
		UserEntity userEntity = new UserEntity(username, password, email, nickname);
		return User.from(userEntity);
	}

	@Test
	@DisplayName("post 생성 테스트")
	void createPost(){
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목","내용");
		User user = testUser();
		//when
		ResponseEntity<ResponseDto<?>> response = postService.createPost(user,postRequestDto);
		//then
		GetPostResponseDto responseDto = (GetPostResponseDto) response.getBody().getData();
		assertEquals(responseDto.getContent(),postRequestDto.getContent());
		assertEquals(responseDto.getNickname(),"nick");
		assertEquals(response.getBody().getMessage(),"포스트 작성에 성공하셨습니다.");
	}


}
