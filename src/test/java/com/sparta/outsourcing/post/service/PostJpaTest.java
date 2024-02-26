package com.sparta.outsourcing.post.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.dto.PostResponseDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostJpaTest { //repository 의 기능 및 글자 수 제한을 동시에 보기 위한 통합 테스트
	@Autowired
	PostRepository postRepository;
	@Autowired
	PostService postService;

	private User testUser() {
		String username = "Test";
		String password = "12345678";
		String email = "kkkkkk@naver.com";
		String nickname = "nick";
		UserEntity userEntity = new UserEntity(username, password, email, nickname);
		return User.from(userEntity);
	}


	@Test
	@DisplayName("작성순 전체 게시물 조회 테스트")
	void getPostOrderDateTest() {
		//given
		UserEntity user = testUser().toEntity();
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용145678901234567890123456789");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목2", "내용2");
		PostEntity postEntity1 = new PostEntity(postRequestDto1, user);
		PostEntity postEntity2 = new PostEntity(postRequestDto2, user);
		postRepository.save(postEntity1);
		postRepository.save(postEntity2);
		//when
		ResponseEntity<ResponseDto<?>> response = postService.getOrderDatePost();
		//then
		List<GetPostListResponseDto> posts = (List<GetPostListResponseDto>) response.getBody()
			.getData();
		assertEquals(posts.get(0).getTitle(),"제목1");
		assertEquals(posts.get(0).getContent(), "내용145678901234567890");
		assertEquals(posts.get(1).getTitle(),"제목2");
		assertEquals(posts.get(1).getContent(), "내용2");
		assertEquals(response.getBody().getMessage(),"작성순 전체 게시물 조회에 성공하셨습니다.");
	}

	@Test
	@DisplayName("작성순 전체 게시물 조회 테스트")
	void getPostOrderViewTest() {
		//given
		UserEntity user = testUser().toEntity();
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용145678901234567890123456789");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목2", "내용2");
		PostEntity postEntity1 = new PostEntity(postRequestDto1, user);
		PostEntity postEntity2 = new PostEntity(postRequestDto2, user);
		postRepository.save(postEntity1);
		postRepository.save(postEntity2);
		postService.getPost(2L);
		postService.getPost(2L);
		postService.getPost(2L);
		//when
		ResponseEntity<ResponseDto<?>> response = postService.getOrderViewPost();
		//then
		List<GetPostListResponseDto> posts = (List<GetPostListResponseDto>) response.getBody()
			.getData();
		assertEquals(posts.get(0).getViews(),3L);
		assertEquals(posts.get(0).getTitle(),"제목2");
		assertEquals(posts.get(0).getContent(), "내용2");
		assertEquals(posts.get(1).getTitle(),"제목1");
		assertEquals(posts.get(1).getContent(), "내용145678901234567890");
		assertEquals(response.getBody().getMessage(),"조회순 전체 게시물 조회에 성공하셨습니다.");
	}

	@Test
	@DisplayName("특정 게시물 조회 테스트")
	void getPost(){
		//given
		UserEntity user = testUser().toEntity();
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용145678901234567890123456789");
		PostRequestDto postRequestDto2 = new PostRequestDto("제목2", "내용2");
		PostEntity postEntity1 = new PostEntity(postRequestDto1, user);
		PostEntity postEntity2 = new PostEntity(postRequestDto2, user);
		postRepository.save(postEntity1);
		postRepository.save(postEntity2);
		//when
		ResponseEntity<ResponseDto<?>> response = postService.getPost(2L);
		//then
		GetPostResponseDto postResponseDto = (GetPostResponseDto) response.getBody().getData();
		assertEquals(postResponseDto.getTitle(),"제목2");
		assertEquals(postResponseDto.getContent(),"내용2");
		assertEquals(postResponseDto.getNickname(),"nick");
		assertEquals(response.getBody().getMessage(),"게시물 조회에 성공하셨습니다.");
	}


}