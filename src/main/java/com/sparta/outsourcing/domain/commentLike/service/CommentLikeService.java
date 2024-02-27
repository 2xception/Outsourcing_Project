package com.sparta.outsourcing.domain.commentLike.service;

import com.sparta.outsourcing.domain.comment.model.Comment;
import com.sparta.outsourcing.domain.comment.repository.CommentRepository;
import com.sparta.outsourcing.domain.commentLike.entity.CommentLikeEntity;
import com.sparta.outsourcing.domain.commentLike.repository.CommentLikeRepository;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentLikeService {

  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final CommentLikeRepository commentLikeRepository;

  public void likeComment(long commentId, User user) {

    Comment findComment = commentRepository.findById(commentId);

    User findUser = userRepository.userBy(user.toEntity().getUsername());

    Optional<CommentLikeEntity> liked = commentLikeRepository.findByCommentAndUser(
        commentId, user.toEntity().getUserId());

    if (liked.isPresent()) {
      throw new IllegalArgumentException("이미 좋아요 눌렀습니다.");
    }

    commentLikeRepository.like(new CommentLikeEntity(findComment, findUser));
  }

  public void deleteLike(long commentId, User user) {
    commentRepository.findById(commentId);
    userRepository.userBy(user.toEntity().getUsername());

    Optional<CommentLikeEntity> liked = commentLikeRepository.findByCommentAndUser(
        commentId, user.toEntity().getUserId());

    if (liked.isEmpty()) {
      throw new IllegalArgumentException("좋아요를 누르지 않았습니다.");
    }

    commentLikeRepository.deleteLike(liked.orElseThrow());

  }
}
