package com.sparta.outsourcing.domain.follow.repository;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

  private final FollowJpaRepository followJpaRepository;

  @Override
  public List<FollowEntity> findAllByFollower(UserEntity follower) {
    return followJpaRepository.findAllByFollower(follower);
  }

  @Override
  public Optional<FollowEntity> findByFollowerAndFollowing(UserEntity follower, UserEntity following) {
    return followJpaRepository.findByFollowerAndFollowing(follower, following);
  }

  @Override
  public void save(FollowEntity followEntity) {
    followJpaRepository.save(followEntity);
  }

  @Override
  public void delete(FollowEntity followEntity) {
    followJpaRepository.delete(followEntity);
  }

  @Override
  public List<FollowEntity> findAllByFollowing(UserEntity following) {
    return followJpaRepository.findAllByFollowing(following);
  }
}
