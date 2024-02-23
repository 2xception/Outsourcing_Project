package com.sparta.outsourcing.domain.commentLike.repository;

import com.sparta.outsourcing.domain.commentLike.entity.CommentLikeEntity;
import com.sparta.outsourcing.domain.commentLike.model.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeJpaRepository extends JpaRepository<CommentLikeEntity, Long> {

}
