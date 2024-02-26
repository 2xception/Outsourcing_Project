package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;

public interface PostRepository {

    Post findByPostId(Long id);

    void save(PostEntity todoEntity);

    void delete(Post post);

    void update(Post post);


}
