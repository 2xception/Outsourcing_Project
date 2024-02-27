package com.sparta.outsourcing.domain.commentLike.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeByComment {

  long commentId;
  long likes;

}
