package com.sparta.outsourcing.domain.postLike.service;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.postLike.repository.PostLikeRepository;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {

  private final PostLikeRepository postLikeRepository;

  private final PostRepository postRepository;

  //게시물 좋아요 추가
  public void addLike(Long postId, User user) {
    PostEntity post = postRepository.finById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지않는 게시글입니다."));
    Optional<PostLikeEntity> postLikeEntity = postLikeRepository.findByIdAndUser(postId, user);
    if(postLikeEntity.isEmpty()) {
      postLikeRepository.save(new PostLikeEntity(post, user.toEntity()));
    } else {
      throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");
    }
  }

  //게시물 좋아요 삭제
  public void deleteLike(Long postId, User user) {
    PostEntity post = postRepository.finById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지않는 게시글입니다."));
    Optional<PostLikeEntity> postLikeEntity = postLikeRepository.findByIdAndUser(postId, user);
    if(postLikeEntity.isEmpty()) {
      throw new IllegalArgumentException("좋아요를 누르지 않은 게시글입니다.");
    } else {
      postLikeRepository.delete(postLikeEntity.get());
    }
  }
}
