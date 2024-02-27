package com.sparta.outsourcing.domain.commentLike.controller;

import com.sparta.outsourcing.domain.commentLike.service.CommentLikeService;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentLikeController {

  private final CommentLikeService commentLikeService;

  @PostMapping("comments/{commentId}/likes")
  public ResponseEntity<ResponseDto<Void>> likeComment(
      @PathVariable("commentId") long commentId,
      @UserInfo User user
  ) {

    commentLikeService.likeComment(commentId, user);

    return ResponseEntity.ok()
        .body(ResponseDto.<Void>builder()
            .message("댓글 좋아요 성공")
            .build()
        );
  }

  @DeleteMapping("comments/{commentId}/likes")
  public ResponseEntity<ResponseDto<Void>> deleteLike(
      @PathVariable("commentId") long commentId,
      @UserInfo User user
  ) {
    commentLikeService.deleteLike(commentId, user);

    return ResponseEntity.ok()
        .body(ResponseDto.<Void>builder()
            .message("댓글 좋아요 삭제 성공")
            .build()
        );
  }

}
