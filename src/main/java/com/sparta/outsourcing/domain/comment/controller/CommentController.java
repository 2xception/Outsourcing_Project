package com.sparta.outsourcing.domain.comment.controller;

import com.sparta.outsourcing.domain.comment.dto.CommentRequestDto;
import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.service.CommentService;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("posts/{postId}/comments")
  public ResponseEntity<ResponseDto<CommentResponseDto>> addComment(
      @PathVariable("postId") long postId,
      @RequestBody CommentRequestDto request,
      @UserInfo User user
  ) {

    CommentResponseDto response = commentService.addComment(postId, request, user);

    return ResponseEntity.ok()
        .body(ResponseDto.<CommentResponseDto>builder()
            .message("댓글 작성 성공")
            .data(response)
            .build()
        );
  }

  @GetMapping("posts/{postId}/comments")
  public ResponseEntity<ResponseDto<List<CommentResponseDto>>> getComments(
      @PathVariable("postId") long postId
  ) {
    List<CommentResponseDto> response = commentService.getComments(postId);

    return ResponseEntity.ok()
        .body(ResponseDto.<List<CommentResponseDto>>builder()
            .message("댓글 조회 성공")
            .data(response)
            .build()
        );
  }

  @PutMapping("comments/{commentId}")
  public ResponseEntity<ResponseDto<CommentResponseDto>> updateComment(
      @PathVariable("commentId") long commentId,
      @RequestBody CommentRequestDto request,
      @UserInfo User user
  ) {

    CommentResponseDto response = commentService.updateComment(commentId, request, user);

    return ResponseEntity.ok()
        .body(ResponseDto.<CommentResponseDto>builder()
            .message("댓글 수정 성공")
            .data(response)
            .build()
        );
  }

  @DeleteMapping("comments/{commentId}")
  public ResponseEntity<ResponseDto<Void>> deleteComment(
      @PathVariable("commentId") long commentId,
      @UserInfo User user
  ) {

    commentService.deleteComment(commentId, user);

    return ResponseEntity.ok()
        .body(ResponseDto.<Void>builder()
            .message("댓글 삭제 성공")
            .build()
        );
  }

}
