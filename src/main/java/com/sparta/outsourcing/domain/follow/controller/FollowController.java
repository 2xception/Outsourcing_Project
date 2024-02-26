package com.sparta.outsourcing.domain.follow.controller;

import com.sparta.outsourcing.domain.follow.service.FollowService;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class FollowController {

  private final FollowService followService;

  //팔로우
  @PostMapping("/{id}/follows")
  public ResponseEntity<ResponseDto> follow (@UserInfo User user, @PathVariable Long id) {
    followService.follow(user, id);
    return ResponseEntity.ok()
        .body(ResponseDto.builder()
            .message("팔로우 성공")
            .build());
  }

  //언팔로우
  @DeleteMapping("/{id}/follows")
  public ResponseEntity<ResponseDto> unfollow (@UserInfo User user, @PathVariable Long id) {
    followService.unfollow(user, id);
    return ResponseEntity.ok()
        .body(ResponseDto.builder()
            .message("팔로우 성공")
            .build());
  }
}
