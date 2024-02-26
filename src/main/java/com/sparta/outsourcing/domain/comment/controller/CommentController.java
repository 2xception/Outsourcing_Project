package com.sparta.outsourcing.domain.comment.controller;

import com.sparta.outsourcing.domain.comment.dto.CommentRequestDto;
import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.service.CommentService;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

}
