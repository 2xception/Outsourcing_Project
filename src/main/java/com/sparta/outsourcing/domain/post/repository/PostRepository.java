package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import java.util.Optional;


public interface PostRepository {

	Optional<PostEntity> findByPostId(Long id);

	void save(PostEntity todoEntity);

	void delete(Post post);

	void update(Post post);

	List<PostEntity> findAllByOrderByCreatedAtDesc();

	List<PostEntity> findAllByOrderByViewsDesc();

}
