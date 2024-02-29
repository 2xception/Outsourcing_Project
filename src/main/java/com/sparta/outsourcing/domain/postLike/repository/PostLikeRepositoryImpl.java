package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
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
  public List<PostLikeEntity> findAllByUserEntity(UserEntity userEntity) {
    return postLikeJpaRepository.findAllByUserEntity(userEntity);
  }

  @Override
  public Optional<PostLikeEntity> findByUserEntityAndPostEntity(UserEntity userEntity, PostEntity postEntity) {
    return postLikeJpaRepository.findByUserEntityAndPostEntity(userEntity, postEntity);
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