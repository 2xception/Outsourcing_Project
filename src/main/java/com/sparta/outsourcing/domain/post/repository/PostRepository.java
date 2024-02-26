package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface PostRepository {

  List<PostEntity> findAll(Sort postLikeList);
}
