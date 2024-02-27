package com.sparta.outsourcing.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.access.AccessDeniedException;

@MockitoSettings(strictness = Strictness.LENIENT)
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
		GetPostResponseDto response = postService.createPost(user, postRequestDto);
		//then
		assertEquals(response.getTitle(), postRequestDto.getTitle());
		assertEquals(response.getContent(), postRequestDto.getContent());
		assertEquals(response.getNickname(), "nick");
	}

	@Test
	@DisplayName("특정 게시물 조회 테스트")
	void getPost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		User user = testUser();
		PostEntity postEntity = new PostEntity(postRequestDto, user.toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Post.from(postEntity));
		//when
		GetPostResponseDto response = postService.getPost(postEntity.getPostId());
		//then
		assertEquals(response.getTitle(), postEntity.getTitle());
		assertEquals(response.getContent(), postRequestDto.getContent());
	}


	@Test
	@DisplayName("게시물 수정 테스트")
	void updatePost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목수정", "내용수정");
		User user = testUser();
		PostEntity postEntity = new PostEntity(postRequestDto, user.toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Post.from(postEntity));
		//when
		GetPostResponseDto response = postService.updatePost(postEntity.getPostId(), user,
			postRequestDto2);
		//then
		assertEquals(response.getContent(), postRequestDto2.getContent());
		assertEquals(response.getTitle(), postRequestDto2.getTitle());
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
			Post.from(postEntity));
		//when
		Exception exception = assertThrows(AccessDeniedException.class,
			() -> postService.updatePost(postEntity.getPostId(), user, postRequestDto2));
		//then
		assertEquals(exception.getMessage(), "해당 게시물을 수정/삭제 하실 권한이 없습니다.");
	}

	@Test
	@DisplayName("게시물 삭제 테스트")
	void deletePost() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		User user = testUser();
		PostEntity postEntity = new PostEntity(postRequestDto, user.toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Post.from(postEntity));
		//when
		postService.deletePost(postEntity.getPostId(), user);
		//then
	}

	@Test
	@DisplayName("게시물 삭제 실패 테스트 - forBidden")
	void deletePostFail2() {
		//given
		PostRequestDto postRequestDto = new PostRequestDto("제목", "내용");
		User user = new User(1L, "실패", "12345678", "test@naver.com", "", "");
		PostEntity postEntity = new PostEntity(postRequestDto, testUser().toEntity());
		given(postRepository.findByPostId(postEntity.getPostId())).willReturn(
			Post.from(postEntity));
		//when
		Exception exception = assertThrows(AccessDeniedException.class,
			() -> postService.deletePost(postEntity.getPostId(), user));
		//then
		assertEquals(exception.getMessage(), "해당 게시물을 수정/삭제 하실 권한이 없습니다.");
	}

	@Test
	@DisplayName("전체 게시물 조회 테스트")
	void getPostOrderDateTest() {
		//given
		UserEntity user = testUser().toEntity();
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용145678901234567890123456789");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목2", "내용2");
		PostEntity postEntity1 = new PostEntity(postRequestDto1, user);
		PostEntity postEntity2 = new PostEntity(postRequestDto2, user);
		List<PostEntity> response = new ArrayList<>();
		response.add(postEntity1);
		response.add(postEntity2);
		given(postRepository.findAllByOrderByCreatedAtDesc()).willReturn(response);
		//when
		List<GetPostListResponseDto> result = postService.getOrderDatePost();
		//then
		assertEquals(result.get(0).getTitle(), "제목1");
		assertEquals(result.get(0).getContent(), "내용145678901234567890");
		assertEquals(result.get(1).getTitle(), "제목2");
		assertEquals(result.get(1).getContent(), "내용2");
	}

}
