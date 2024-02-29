package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;

public interface PostLikeRepository {

  List<PostLikeEntity> findAllByUserEntity(UserEntity userEntity);

  Optional<PostLikeEntity> findByUserEntityAndPostEntity(UserEntity userEntity, PostEntity postEntity);

  void save(PostLikeEntity postLikeEntity);

  void delete(PostLikeEntity postLikeEntity);
}
