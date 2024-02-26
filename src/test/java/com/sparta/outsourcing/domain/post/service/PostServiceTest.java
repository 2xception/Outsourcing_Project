package com.sparta.outsourcing.domain.post.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.postLike.repository.PostLikeRepository;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 서버의 PORT 를 랜덤으로 설정합니다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// 테스트 인스턴스의 생성 단위를 클래스로 변경합니다. (필드값을 각각의 테스트에서 사용가능해짐 (테스트는 따로 돌기때문에))
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostServiceTest {

  @Autowired
  PostService postService;

  @Autowired
  PostLikeRepository postLikeRepository;

  User user = new User();

  @Test
  @Order(1)
  @DisplayName("게시물 전체조회 - 좋아요순")
  public void getPostsSortedByLikesTest() {
    // given
    User user = new User(
        1L, "name", "1234", "user@email.com", "nickname", "photo"); // User 객체 생성

    PostEntity postEntity = new PostEntity();

    PostLikeEntity postLikeEntity1 = new PostLikeEntity(); // PostLikeEntity 객체 생성 및 초기화
    PostLikeEntity postLikeEntity2 = new PostLikeEntity(); // PostLikeEntity 객체 생성 및 초기화
    List<PostLikeEntity> postLikeList = Arrays.asList(postLikeEntity1, postLikeEntity2);
    when(postLikeRepository.findAllByUser(user)).thenReturn(postLikeList);

    // when
    List<GetPostListResponseDto> result = postService.getPostsSortedByLikes(user);

    // then
    assertEquals(postLikeList.size(), result.size());
  }
}