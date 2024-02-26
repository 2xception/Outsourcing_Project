package com.sparta.outsourcing.domain.comment.service;

import com.sparta.outsourcing.domain.comment.dto.CommentRequestDto;
import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.comment.repository.CommentRepository;
import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  public CommentResponseDto addComment(long postId, CommentRequestDto request, User user) {

    PostEntity post = postRepository.findByPostId(postId)
        .orElseThrow(() -> new IllegalArgumentException("없는 게시글입니다."));
    User findUser = userRepository.userBy(user.toEntity().getUsername());
    CommentEntity savedComment = commentRepository.save(
        new CommentEntity(request, Post.from(post), findUser));

    return new CommentResponseDto(savedComment);
  }

  public List<CommentResponseDto> getComments(long postId) {

    if(postRepository.findByPostId(postId).isEmpty()){
      throw new IllegalArgumentException("없는 게시글입니다.");
    }

    return commentRepository.findByPostEntityPostId(postId).stream().map(CommentResponseDto::new)
        .toList();
  }

}
