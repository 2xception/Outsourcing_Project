package com.sparta.outsourcing.domain.commentLike.model;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.commentLike.entity.CommentLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentLike {

  private Long commentLikeId;
  private UserEntity userEntity;
  private CommentEntity commentEntity;

  public static CommentLike from(final CommentLikeEntity commentLikeEntity) {
    return new CommentLike(
        commentLikeEntity.getCommentLikeId(),
        commentLikeEntity.getUserEntity(),
        commentLikeEntity.getCommentEntity()
    );
  }

  public CommentLikeEntity toEntity() {
    return new CommentLikeEntity(
        commentLikeId,
        userEntity,
        commentEntity
    );
  }

}
