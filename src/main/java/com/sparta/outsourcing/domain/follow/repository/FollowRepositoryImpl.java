package com.sparta.outsourcing.domain.follow.repository;

import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

  private final FollowJpaRepository followJpaRepository;

  @Override
  public List<UserEntity> findAllByUser(User user) {
    return followJpaRepository.findAllByUser(user);
  }
}
