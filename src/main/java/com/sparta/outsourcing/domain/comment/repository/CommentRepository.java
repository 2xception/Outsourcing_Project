package com.sparta.outsourcing.domain.comment.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;

public interface CommentRepository {

  CommentEntity save(CommentEntity commentEntity);

}
