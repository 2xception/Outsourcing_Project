package com.sparta.outsourcing.post.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostJpaRepository;
import com.sparta.outsourcing.domain.post.repository.PostRepositoryImpl;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.repository.UserJpaRepository;
import com.sparta.outsourcing.domain.user.repository.UserRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostRepositoryTest {

	@Autowired
	private PostJpaRepository postJpaRepository;

	private PostRepositoryImpl postRepository;

	@Autowired
	private UserJpaRepository userJpaRepository;

	private UserRepositoryImpl userRepository;

	@BeforeEach
	void setUp() {
		postRepository = new PostRepositoryImpl(postJpaRepository);
		userRepository = new UserRepositoryImpl(userJpaRepository);
	}

	private PostEntity testPost() {
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용1");
		String username = "Test";
		String password = "12345678";
		String email = "kkkkk@naver.com";
		String nickname = "nick";
		UserEntity userEntity = new UserEntity(username, password, email, nickname);
		userRepository.save(userEntity);
		return new PostEntity(postRequestDto1, userEntity);
	}

	private void testListPost() {
		PostEntity postEntity1 = testPost();
		postEntity1.setViews(10L);
		postRepository.save(postEntity1);
		PostRequestDto postRequestDto2 = new PostRequestDto("제목2", "내용2");
		UserEntity userEntity2 = new UserEntity("test2", "123456789", "test2@naver.com", "test2");
		userRepository.save(userEntity2);
		PostEntity postEntity2 = new PostEntity(postRequestDto2, userEntity2);
		postEntity2.setViews(3L);
		postRepository.save(postEntity2);
		PostRequestDto postRequestDto3 = new PostRequestDto("제목3", "내용3");
		UserEntity userEntity3 = new UserEntity("test3", "1234567891", "test3@naver.com", "test3");
		userRepository.save(userEntity3);
		PostEntity postEntity3 = new PostEntity(postRequestDto3, userEntity3);
		postEntity3.setViews(5L);
		postRepository.save(postEntity3);
	}

	@Test
	@DisplayName("게시물 저장 테스트")
	void savePost() {
		//given
		PostEntity postEntity = testPost();
		//when
		postRepository.save(postEntity);
		//then
		assertEquals(postEntity.getPostId(),
			postRepository.findByPostId(postEntity.getPostId()).getPostId());
	}

	@Test
	@DisplayName("게시물 찾기 테스트")
	void findPostId() {
		//given
		PostEntity postEntity = testPost();
		postRepository.save(postEntity);
		//when
		Post response = postRepository.findByPostId(postEntity.getPostId());
		//then
		assertEquals(postEntity.getPostId(), response.getPostId());
	}

	@Test
	@DisplayName("게시물 찾기 실패 테스트")
	void findPostIdFail() {
		//given
		PostEntity postEntity = testPost();
		postRepository.save(postEntity);
		//when
		Exception exception = assertThrows(EntityNotFoundException.class,
			() -> postRepository.findByPostId(10L));
		//then
		assertEquals(exception.getMessage(), "해당 id값의 게시글이 없습니다.");
	}

	@Test
	@DisplayName("게시물 수정 테스트")
	void updatePost() {
		// given
		PostEntity postEntity = testPost();
		postRepository.save(postEntity);
		Long postId = postEntity.getPostId();

		// when
		PostEntity updatedPostEntity = postRepository.findByPostId(postId).toEntity();
		updatedPostEntity.setTitle("수정된 제목");
		updatedPostEntity.setContent("수정된 내용");
		postRepository.update(Post.from(updatedPostEntity));

		// then
		PostEntity retrievedPostEntity = postRepository.findByPostId(postId).toEntity();
		assertEquals(updatedPostEntity.getTitle(), retrievedPostEntity.getTitle());
		assertEquals(updatedPostEntity.getContent(), retrievedPostEntity.getContent());
	}

	@Test
	@DisplayName("게시물 삭제 테스트")
	void deletePost() {
		//given
		PostEntity postEntity = testPost();
		postRepository.save(postEntity);
		Long postId = postEntity.getPostId();

		//when
		postRepository.delete(Post.from(postEntity));
		Exception exception = assertThrows(EntityNotFoundException.class,
			() -> postRepository.findByPostId(postId));
		//then
		assertEquals(exception.getMessage(), "해당 id값의 게시글이 없습니다.");
	}

	@Test
	@DisplayName("게시물 생성일자 내림차순 조회 테스트")
	void findAllByOrderByCreatedAtDesc() {
		//given
		testListPost();
		//when
		List<PostEntity> postEntities = postRepository.findAllByOrderByCreatedAtDesc();
		//then
		assertEquals(3, postEntities.size());
		assertTrue(
			postEntities.get(0).getCreatedAt().compareTo(postEntities.get(1).getCreatedAt()) >= 0);
		assertTrue(
			postEntities.get(1).getCreatedAt().compareTo(postEntities.get(2).getCreatedAt()) >= 0);
	}

	@Test
	@DisplayName("게시물 조회수 내림차순 조회 테스트")
	void findAllByOrderByViewsDesc() {
		//given
		testListPost();
		//when
		List<PostEntity> postEntities = postRepository.findAllByOrderByViewsDesc();
		//then
		assertEquals(3, postEntities.size());
		assertTrue(postEntities.get(0).getViews() >= postEntities.get(1).getViews());
		assertTrue(postEntities.get(1).getViews() >= postEntities.get(2).getViews());
	}
}
