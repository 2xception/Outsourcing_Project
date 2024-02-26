package com.sparta.outsourcing.domain.follow.repository;

import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;

public interface FollowRepository {

  List<UserEntity> findAllByUser(User user);
}
