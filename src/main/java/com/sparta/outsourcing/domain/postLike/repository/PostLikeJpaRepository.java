package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeJpaRepository extends JpaRepository<PostLikeEntity, Long> {

  List<PostLikeEntity> findAllByUser(User user);

  Optional<PostLikeEntity> findByIdAndUser(Long postId, User user);
}
