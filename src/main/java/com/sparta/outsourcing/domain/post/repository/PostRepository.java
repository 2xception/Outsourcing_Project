package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

public interface PostRepository {

  List<PostEntity> findAll(Sort postLikeList);

  Optional<PostEntity> finById(Long postId);

}
