package com.sparta.outsourcing.domain.comment.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import java.util.List;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;

public interface CommentRepository {

  CommentEntity save(CommentEntity commentEntity);

  List<CommentEntity> findByPostEntityPostId(long postId);

  List<CommentEntity> findByUserEntityUserId(Long userId);
}
