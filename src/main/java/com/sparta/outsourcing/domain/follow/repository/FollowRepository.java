package com.sparta.outsourcing.domain.follow.repository;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.follow.model.Follow;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;

public interface FollowRepository {

  List<FollowEntity> findAllByFollower(UserEntity follower);

  Optional<FollowEntity> findByFollowerAndFollowing(UserEntity follower, UserEntity following);

  void save(FollowEntity followEntity);

  void delete(FollowEntity followEntity);
}
