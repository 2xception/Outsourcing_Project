package com.sparta.outsourcing.domain.follow.service;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.follow.repository.FollowRepository;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final FollowRepository followRepository;

  private final UserRepository userRepository;

  //팔로우
  public void follow(User user, Long id) {
    UserEntity findUser = userRepository.finById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    Optional<FollowEntity> follow = followRepository.findByUserAndId(user,id);
    if(follow.isEmpty()) {
      followRepository.save(new FollowEntity(user.toEntity(), findUser));
    } else {
      throw new IllegalArgumentException("이미 팔로우 하고 있는 사용자입니다.");
    }

  }

  //언팔로우
  public void unfollow(User user, Long id) {
    Optional<FollowEntity> follow = followRepository.findByUserAndId(user,id);
    if(follow.isEmpty()) {
      throw new IllegalArgumentException("팔로우 하고 있지 않은 사용자입니다.");
    } else {
      followRepository.delete(follow.get());
    }
  }
}
