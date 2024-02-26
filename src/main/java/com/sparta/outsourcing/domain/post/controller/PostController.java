package com.sparta.outsourcing.domain.post.controller;

import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  //게시물전체조회 - 내가 좋아요표시
  @GetMapping("/my-likes")
  public ResponseEntity<ResponseDto<List<GetPostListResponseDto>>> getPostsSortedByLikes (
      @UserInfo User user
  ) {
    return ResponseEntity.ok()
        .body(ResponseDto.<List<GetPostListResponseDto>>builder()
            .message("좋아요 표시한 게시물 전체 조회 성공")
            .data(postService.getPostsSortedByLikes(user))
            .build());
  }

  //게시물전체조회 - 내가 팔로우한
  @GetMapping("/order-views")
  public ResponseEntity<ResponseDto<List<GetPostListResponseDto>>> getPostsSortedByFollow (
      @UserInfo User user
  ) {
    return ResponseEntity.ok()
        .body(ResponseDto.<List<GetPostListResponseDto>>builder()
            .message("팔로우한 유저 게시물 조회 성공")
            .data(postService.getPostsSortedByFollow(user))
            .build());
  }

  //게시물전체조회 - 좋아요순
  @GetMapping("/order-likes")
  public ResponseEntity<ResponseDto<List<GetPostListResponseDto>>> getPosts () {
    return ResponseEntity.ok()
        .body(ResponseDto.<List<GetPostListResponseDto>>builder()
            .message("팔로우한 유저 게시물 조회 성공")
            .data(postService.getPosts())
            .build());
  }

  //게시물 좋아요 추가

  //게시물 좋아요 삭제

  //팔로우

  //언팔로우
}
