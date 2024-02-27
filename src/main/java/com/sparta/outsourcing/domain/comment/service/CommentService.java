package com.sparta.outsourcing.domain.comment.service;

import com.sparta.outsourcing.domain.comment.dto.CommentRequestDto;
import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.comment.model.Comment;
import com.sparta.outsourcing.domain.comment.repository.CommentRepository;
import com.sparta.outsourcing.domain.commentLike.dto.CommentLikeByComment;
import com.sparta.outsourcing.domain.commentLike.repository.CommentLikeRepository;
import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;
  private final CommentLikeRepository commentLikeRepository;


  public CommentResponseDto addComment(long postId, CommentRequestDto request, User user) {

    PostEntity post = postRepository.findByPostId(postId)
        .orElseThrow(() -> new NoSuchElementException("없는 게시글입니다."));
    User findUser = userRepository.userBy(user.toEntity().getUsername());

    CommentEntity savedComment = commentRepository.save(
        new CommentEntity(request, Post.from(post), findUser));

    return Comment.from(savedComment).toResponse(0);
  }

  @Transactional(readOnly = true)
  public List<CommentResponseDto> getComments(long postId) {

    if (postRepository.findByPostId(postId).isEmpty()) {
      throw new NoSuchElementException("없는 게시글입니다.");
    }

    List<CommentEntity> commentList = commentRepository.findByPostEntityPostId(postId);

    List<Long> commentIdList = commentList.stream()
        .mapToLong(CommentEntity::getCommentId)
        .boxed().toList();

    Map<Long, Long> map = commentLikeRepository.countAllByComment(commentIdList).stream()
        .collect(Collectors.toMap(
            CommentLikeByComment::getCommentId,
            CommentLikeByComment::getLikes
        ));

    return commentList.stream()
        .map(commentEntity -> {
              long count = map.getOrDefault(commentEntity.getCommentId(), 0L);
              return Comment.from(commentEntity).toResponse(count);
            }
        ).collect(Collectors.toList());
  }

  public CommentResponseDto updateComment(long commentId, CommentRequestDto request, User user) {

    Comment findComment = commentRepository.findById(commentId);

    if (!User.from(findComment.getUserEntity()).equals(user)) {
      throw new BadCredentialsException("작성자만 수정가능합니다.");
    }

    long count = commentLikeRepository.countByComment(findComment.toEntity());

    findComment.updateComment(request);
    commentRepository.update(findComment.toEntity());

    return findComment.toResponse(count);
  }

  public void deleteComment(long commentId, User user) {
    Comment findComment = commentRepository.findById(commentId);

    if (!User.from(findComment.getUserEntity()).equals(user)) {
      throw new BadCredentialsException("작성자만 삭제 가능합니다.");
    }

    commentRepository.deleteComment(findComment.toEntity());
  }

}
