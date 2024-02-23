package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeJpaRepository extends JpaRepository<PostLikeEntity, Long> {

}
