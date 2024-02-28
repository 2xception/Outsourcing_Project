package com.sparta.outsourcing.domain.comment.model;

import com.sparta.outsourcing.domain.comment.dto.CommentRequestDto;
import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  private Long commentId;
  private UserEntity userEntity;
  private PostEntity postEntity;
  private String comment;

  public static Comment from(final CommentEntity commentEntity) {
    return new Comment(
        commentEntity.getCommentId(),
        commentEntity.getUserEntity(),
        commentEntity.getPostEntity(),
        commentEntity.getComment()
    );
  }

  public CommentResponseDto toResponse(long count) {
    return new CommentResponseDto(
        userEntity.getNickname(),
        comment,
        count
    );
  }

  public CommentEntity toEntity() {
    return new CommentEntity(
        commentId,
        userEntity,
        postEntity,
        comment
    );
  }

  public void updateComment(CommentRequestDto request) {
    this.comment = request.getComment();
  }
}
