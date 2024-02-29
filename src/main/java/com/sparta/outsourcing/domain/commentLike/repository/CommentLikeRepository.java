package com.sparta.outsourcing.domain.commentLike.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.commentLike.dto.CommentLikeByComment;
import com.sparta.outsourcing.domain.commentLike.entity.CommentLikeEntity;
import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository {

  Optional<CommentLikeEntity> findByCommentAndUser(long commentId, long userId);

  void like(CommentLikeEntity commentLikeEntity);

  void deleteLike(CommentLikeEntity commentLikeEntity);

  long countByComment(CommentEntity commentEntity);

  List<CommentLikeByComment> countAllByComment(List<Long> commentIdList);
}
