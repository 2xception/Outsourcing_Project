package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final PostJpaRepository postJpaRepository;

  @Override
  public Post findPostBy(long postId) {
    return Post.from(postJpaRepository.findById(postId).orElseThrow(
        () -> new IllegalArgumentException("없는 게시글입니다."))
    );
  }

}
