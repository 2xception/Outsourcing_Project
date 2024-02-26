package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.repository.PostJpaRepository;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepository {

  private final PostJpaRepository postJpaRepository;

  @Override
  public List<PostLikeEntity> findAllByUser(User user) {
    return postJpaRepository.findAllByUser(user);
  }
}