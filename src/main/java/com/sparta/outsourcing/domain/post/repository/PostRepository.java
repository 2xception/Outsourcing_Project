package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;

public interface PostRepository {

	Post findByPostId(Long id);

	void save(PostEntity todoEntity);

	void delete(Post post);

	void update(Post post);

	List<PostEntity> findAllByOrderByCreatedAtDesc();

	List<PostEntity> findAllByOrderByViewsDesc();

}
