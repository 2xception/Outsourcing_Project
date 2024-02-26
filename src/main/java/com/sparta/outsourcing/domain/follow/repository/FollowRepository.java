package com.sparta.outsourcing.domain.follow.repository;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;

public interface FollowRepository {

  List<UserEntity> findAllByUser(User user);

  Optional<FollowEntity> findByUserAndId(User user, Long id);

  void save(FollowEntity followEntity);

  void delete(FollowEntity followEntity);
}
