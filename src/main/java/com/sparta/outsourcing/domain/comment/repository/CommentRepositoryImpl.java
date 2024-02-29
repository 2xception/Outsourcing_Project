package com.sparta.outsourcing.domain.comment.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.comment.model.Comment;
import java.util.List;
import java.util.NoSuchElementException;
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

  @Override
  public List<CommentEntity> findByPostEntityPostId(long postId) {
    return commentJpaRepository.findByPostEntityPostId(postId);
  }

  @Override
  public Comment findById(long commentId) {
    return Comment.from(commentJpaRepository.findById(commentId)
        .orElseThrow(() -> new NoSuchElementException("없는 댓글입니다.")));
  }

  @Override
  public void deleteComment(CommentEntity commentEntity) {
    commentJpaRepository.delete(commentEntity);
  }

  @Override
  public void update(CommentEntity commentEntity) {
    commentJpaRepository.saveAndFlush(commentEntity);
  }


  @Override
  public List<CommentEntity> findByUserEntityUserId(Long userId) {
    return commentJpaRepository.findByUserEntityUserId(userId);
  }
}
