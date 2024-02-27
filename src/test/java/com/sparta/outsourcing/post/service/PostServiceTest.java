package com.sparta.outsourcing.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;

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
	@DisplayName("게시물 생성 테스트")
	void createPost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		User user = testUser();
		//when
		ResponseEntity<ResponseDto<?>> response = postService.createPost(user, postRequestDto);
		//then
		GetPostResponseDto responseDto = (GetPostResponseDto) response.getBody().getData();
		assertEquals(responseDto.getContent(), postRequestDto.getContent());
		assertEquals(responseDto.getNickname(), "nick");
		assertEquals(response.getBody().getMessage(), "포스트 작성에 성공하셨습니다.");
	}

	@Test
	@DisplayName("특정 게시물 조회 테스트")
	void getPost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		User user = testUser();
		PostEntity postEntity = new PostEntity(postRequestDto, user.toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Optional.of(postEntity));
		//when
		ResponseEntity<ResponseDto<?>> response = postService.getPost(postEntity.getPostId());
		//then
		GetPostResponseDto responseDto = (GetPostResponseDto) response.getBody().getData();
		assertEquals(responseDto.getTitle(), postEntity.getTitle());
		assertEquals(responseDto.getContent(), postRequestDto.getContent());
		assertEquals(response.getBody().getMessage(), "게시물 조회에 성공하셨습니다.");
	}

	@Test
	@DisplayName("특정 게시물 조회 실패 테스트")
	void getPostFail() {
		//given
		//when
		ResponseEntity<ResponseDto<?>> response = postService.getPost(10L);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody().getMessage(), "해당 id값의 게시글이 없습니다.");
	}

	@Test
	@DisplayName("게시물 수정 테스트")
	void updatePost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목수정", "내용수정");
		User user = testUser();
		PostEntity postEntity = new PostEntity(postRequestDto, user.toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn();
		//when
		ResponseEntity<ResponseDto<?>> response = postService.updatePost(postEntity.getPostId(),
			user, postRequestDto2);
		//then
		GetPostResponseDto responseDto = (GetPostResponseDto) response.getBody().getData();
		assertEquals(responseDto.getContent(), postRequestDto2.getContent());
		assertEquals(responseDto.getTitle(), postRequestDto2.getTitle());
		assertEquals(response.getBody().getMessage(), "게시물 수정에 성공하셨습니다.");
	}

	@Test
	@DisplayName("게시물 수정 실패 테스트 - badRequest ")
	void updatePostFail1() {
		//given
		PostRequestDto postRequestDto2 = new PostRequestDto("제목수정", "내용수정");
		User user = testUser();
		//when
		ResponseEntity<ResponseDto<?>> response = postService.updatePost(10L, user,
			postRequestDto2);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody().getMessage(), "해당 id값의 게시글이 없습니다.");
	}

	@Test
	@DisplayName("게시물 수정 실패 테스트 - forBidden ")
	void updatePostFail2() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목수정", "내용수정");
		User user = new User(1L, "실패", "12345678", "test@naver.com", "", "");
		PostEntity postEntity = new PostEntity(postRequestDto, testUser().toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Optional.of(postEntity));
		//when
		ResponseEntity<ResponseDto<?>> response = postService.updatePost(postEntity.getPostId(),
			user, postRequestDto2);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
		assertEquals(response.getBody().getMessage(), "이 게시물을 수정하실 권한이 없습니다.");
	}

	@Test
	@DisplayName("게시물 삭제 테스트")
	void deletePost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		User user = testUser();
		PostEntity postEntity = new PostEntity(postRequestDto, user.toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Optional.of(postEntity));
		//when
		ResponseEntity<ResponseDto<?>> response = postService.deletePost(postEntity.getPostId(),
			user);
		//then
		assertEquals(response.getBody().getMessage(), "게시물 삭제에 성공하셨습니다.");
	}

	@Test
	@DisplayName("게시물 삭제 실패 테스트 - badRequest")
	void deletePostFail1() {
		//given
		User user = testUser();
		//when
		ResponseEntity<ResponseDto<?>> response = postService.deletePost(10L, user);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody().getMessage(), "해당 id값의 게시글이 없습니다.");
	}

	@Test
	@DisplayName("게시물 삭제 실패 테스트 - forBidden")
	void deletePostFail2() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목수정", "내용수정");
		User user = new User(1L, "실패", "12345678", "test@naver.com", "", "");
		PostEntity postEntity = new PostEntity(postRequestDto, testUser().toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Optional.of(postEntity));
		//when
		ResponseEntity<ResponseDto<?>> response = postService.deletePost(postEntity.getPostId(),
			user);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
		assertEquals(response.getBody().getMessage(), "이 게시물을 삭제하실 권한이 없습니다.");
	}

}
