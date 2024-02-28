package com.sparta.outsourcing.domain.post.dto;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostListResponseDto2 {
  private String title;
  private String content;
  private String nickname;
  private Long views;
  private Integer likes;
  public GetPostListResponseDto2(String title, String content, int size, Long views,
      String nickname) {
    this.title = title;
    this.content = content;
    this.likes = size;
    this.views = views;
    this.nickname = nickname;
  }
}
