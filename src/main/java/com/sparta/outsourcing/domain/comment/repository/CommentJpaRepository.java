package com.sparta.outsourcing.domain.comment.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

  List<CommentEntity> findByUserEntityUserId(Long userId);

  List<CommentEntity> findByPostEntityPostId(long postId);

}
