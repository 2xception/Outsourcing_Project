package com.sparta.outsourcing.post.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostJpaRepository;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.post.repository.PostRepositoryImpl;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PostRepositoryTest {
	@Autowired
	private PostRepositoryImpl postRepository;
	@Autowired
	private PostJpaRepository postJpaRepository;

	private PostEntity testPost(){
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용145678901234567890123456789");
		String username = "Test";
		String password = "12345678";
		String email = "kkkkk@naver.com";
		String nickname = "nick";
		UserEntity userEntity = new UserEntity(username, password, email, nickname);
		return new PostEntity(postRequestDto1,userEntity);
	}

	private PostEntity updateTestPost(){
		PostRequestDto postRequestDto1 = new PostRequestDto("제목수정", "내용수정");
		String username = "Test";
		String password = "12345678";
		String email = "kkkkk@naver.com";
		String nickname = "nick";
		UserEntity userEntity = new UserEntity(username, password, email, nickname);
		return new PostEntity(postRequestDto1,userEntity);
	}

	@Test
	@DisplayName("게시물 저장 테스트")
	void savePost(){
		//given
		PostEntity postEntity = testPost();
		//when
		PostEntity postEntity1 = postJpaRepository.save(postEntity);
		//then
		assertEquals(postEntity,postEntity1);
	}

	@Test
	@DisplayName("게시물 찾기 테스트")
	void findPostId(){
		//given
		PostEntity postEntity = testPost();
		postRepository.save(postEntity);
		//when
		Optional<PostEntity> response = postRepository.findByPostId(postEntity.getPostId());
		//then
		assertEquals(postEntity.getPostId(),response.get().getPostId());
	}

	@Test
	@DisplayName("게시물 수정 테스트")
	void updatePost(){
		//given
		PostEntity postEntity1 = testPost();
		postJpaRepository.save(postEntity1);
		postEntity1.setTitle("제목변경");
		postEntity1.setContent("내용변경");
		//when
		PostEntity postEntity2 = postJpaRepository.saveAndFlush(postEntity1);
		//then
		assertEquals(postEntity2.getTitle(),postEntity1.getTitle());
		assertEquals(postEntity2.getContent(),postEntity1.getContent());
	}

	@Test
	@DisplayName("게시물 삭제 테스트")
	void deletePost(){
		//given
		PostEntity postEntity = testPost();
		postJpaRepository.save(postEntity);
		//when
		postJpaRepository.delete(postEntity);
		//then
		assertTrue(postJpaRepository.findById(postEntity.getPostId()).isEmpty());

	}

}
