package com.sparta.outsourcing.post.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.post.repository.PostRepositoryImpl;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostRepositoryTest {
	@Autowired
	private PostRepository postRepository;

	private PostEntity testPost(){
		PostRequestDto postRequestDto1 = new PostRequestDto("제목1", "내용145678901234567890123456789");
		String username = "Test";
		String password = "12345678";
		String email = "kkkkk@naver.com";
		String nickname = "nick";
		UserEntity userEntity = new UserEntity(username, password, email, nickname);
		return new PostEntity(postRequestDto1,userEntity);
	}

	@Test
	@DisplayName("포스트 찾기 테스트")
	void findPostId(){
		//given
		PostEntity postEntity = testPost();
		postRepository.save(postEntity);
		//when
		Optional<PostEntity> response = postRepository.findByPostId(postEntity.getPostId());
		//then
		assertEquals(postEntity,response.get());
	}

}
