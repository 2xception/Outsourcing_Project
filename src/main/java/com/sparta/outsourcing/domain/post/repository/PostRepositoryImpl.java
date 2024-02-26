package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
  private final PostJpaRepository postJpaRepository;

  @Override
  public List<PostEntity> findAll(Sort postLikeList) {
    return postJpaRepository.findAll();
  }
}
