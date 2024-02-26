package com.sparta.outsourcing.domain.postLike.repository;

import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;

public interface PostLikeRepository {

  List<PostLikeEntity> findAllByUser(User user);
}
