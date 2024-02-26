package com.sparta.outsourcing.domain.postLike.controller;

import com.sparta.outsourcing.domain.postLike.service.PostLikeService;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostLikeController {

  private final PostLikeService postLikeService;

  //게시물 좋아요 추가
  @PostMapping("{postId}/likes")
  public ResponseEntity<ResponseDto> addLike (@PathVariable String postId, @UserInfo User user) {
    postLikeService.addLike(postId, user);
    return ResponseEntity.ok()
        .body(ResponseDto.builder()
            .message("게시물 좋아요 추가 성공")
            .build());
  }

  //게시물 좋아요 삭제

  //팔로우

  //언팔로우
}
