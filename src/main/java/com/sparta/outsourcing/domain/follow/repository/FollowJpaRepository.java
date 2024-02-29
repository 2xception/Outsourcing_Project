package com.sparta.outsourcing.domain.follow.repository;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {

  List<FollowEntity> findAllByFollower(UserEntity follower);

  Optional<FollowEntity> findByFollowerAndFollowing(UserEntity follower, UserEntity following);

  List<FollowEntity> findAllByFollowing(UserEntity following);
}
