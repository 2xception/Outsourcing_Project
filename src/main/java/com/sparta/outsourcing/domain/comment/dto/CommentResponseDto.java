package com.sparta.outsourcing.domain.comment.dto;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

  private String nickname;
  private String comment;
  private long likes;

  public CommentResponseDto(CommentEntity savedComment) {
    this.comment = savedComment.getComment();
  }

}
