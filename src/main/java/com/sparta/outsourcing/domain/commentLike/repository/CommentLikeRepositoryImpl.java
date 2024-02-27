package com.sparta.outsourcing.domain.commentLike.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.commentLike.dto.CommentLikeRequestDto;
import com.sparta.outsourcing.domain.commentLike.entity.CommentLikeEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepository {

  private final CommentLikeJpaRepository commentLikeJpaRepository;

  @Override
  public Optional<CommentLikeEntity> findByCommentAndUser(long commentId, long userId) {

    return commentLikeJpaRepository.findByCommentEntityCommentIdAndUserEntityUserId(
        commentId, userId);
  }

  @Override
  public void like(CommentLikeEntity commentLikeEntity) {
    commentLikeJpaRepository.save(commentLikeEntity);
  }

  @Override
  public void deleteLike(CommentLikeEntity commentLikeEntity) {
    commentLikeJpaRepository.delete(commentLikeEntity);
  }

  @Override
  public long countByComment(CommentEntity commentEntity) {
    return commentLikeJpaRepository.countByCommentEntity(commentEntity);
  }

  @Override
  public List<CommentLikeRequestDto> countAllByComment(List<Long> commentIdList) {
    return commentLikeJpaRepository.countAllByCommentEntity(commentIdList);
  }

}
