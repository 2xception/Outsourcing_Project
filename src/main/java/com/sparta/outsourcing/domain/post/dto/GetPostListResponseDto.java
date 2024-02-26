package com.sparta.outsourcing.domain.post.dto;

import lombok.Getter;

@Getter
public class GetPostListResponseDto {

  private String title;

  private String content;

  private Integer likes;

  private Long views;

  private String nickName;

  public GetPostListResponseDto(String title, String content, Integer likes, Long views, String user) {
    this.title = title;
    this.content = content;
    this.likes = likes;
    this.views = views;
    this.nickName = user;
  }

}
