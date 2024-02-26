package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.repository.PostJpaRepository;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepository {


  private final PostLikeJpaRepository postLikeJpaRepository;
  @Override
  public List<PostLikeEntity> findAllByUser(User user) {
    return postLikeJpaRepository.findAllByUser(user);
  }

  @Override
  public Optional<PostLikeEntity> findByIdAndUser(String postId, User user) {
    return postLikeJpaRepository.findByIdAndUser(postId, user);
  }

  @Override
  public void save(PostLikeEntity postLikeEntity) {
    postLikeJpaRepository.save(postLikeEntity);
  }

  @Override
  public void delete(PostLikeEntity postLikeEntity) {
    postLikeJpaRepository.delete(postLikeEntity);
  }
}