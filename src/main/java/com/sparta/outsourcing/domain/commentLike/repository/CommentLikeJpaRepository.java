package com.sparta.outsourcing.domain.commentLike.repository;

import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.commentLike.dto.CommentLikeByComment;
import com.sparta.outsourcing.domain.commentLike.entity.CommentLikeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentLikeJpaRepository extends JpaRepository<CommentLikeEntity, Long> {

  Optional<CommentLikeEntity> findByCommentEntityCommentIdAndUserEntityUserId(long commentId,
      long userId);

  Long countByCommentEntity(CommentEntity commentEntity);

  @Query("select new com.sparta.outsourcing.domain.commentLike.dto.CommentLikeByComment(c.commentEntity.commentId, count(c)) from CommentLikeEntity c where c.commentEntity.commentId in :list group by c.commentEntity.commentId")
  List<CommentLikeByComment> countAllByCommentEntity(@Param("list") List<Long> commentIdList);
}
