package com.sparta.outsourcing.domain.comment.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final CommentJpaRepository commentJpaRepository;

  @Override
  public CommentEntity save(CommentEntity commentEntity) {
    return commentJpaRepository.save(commentEntity);
  }
  
}
