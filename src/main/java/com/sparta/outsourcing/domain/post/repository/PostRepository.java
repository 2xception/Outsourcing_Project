package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.model.Post;

public interface PostRepository {

  Post findPostBy(long postId);

}
