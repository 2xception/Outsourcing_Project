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
  public List<UserEntity> findAllByUser(User user) {
    return followJpaRepository.findAllByUser(user);
  }

  @Override
  public Optional<FollowEntity> findByUserAndId(User user, Long id) {
    return followJpaRepository.findByUserAndId(user, id);
  }

  @Override
  public void save(FollowEntity followEntity) {
    followJpaRepository.save(followEntity);
  }

  @Override
  public void delete(FollowEntity followEntity) {
    followJpaRepository.delete(followEntity);
  }
}
