package com.sparta.outsourcing.domain.post.model;

import com.sparta.outsourcing.domain.post.entity.PostEntity;

public class Post {

  public static Post from(PostEntity postEntity) {
    return new Post();
  }

  public PostEntity toEntity() {
    return new PostEntity();
  }
}
