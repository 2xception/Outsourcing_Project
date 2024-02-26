package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import java.util.Optional;
import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
  private final PostJpaRepository postJpaRepository;

  @Override
  public List<PostEntity> findAll(Sort postLikeList) {
    return postJpaRepository.findAll();
  }

  @Override
  public Optional<PostEntity> finById(Long postId) {
    return postJpaRepository.findById(postId);
  }

	@Override
	public Optional<PostEntity> findByPostId(Long id) {
		return postJpaRepository.findById(id);
	}

	@Override
	public void save(PostEntity postEntity) {
		postJpaRepository.save(postEntity);
	}

	@Override
	public void delete(Post post) {
		postJpaRepository.delete(post.toEntity());
	}

	@Override
	public void update(Post post) {
		postJpaRepository.saveAndFlush(post.toEntity());
	}

	@Override
	public List<PostEntity> findAllByOrderByCreatedAtDesc() {
		return postJpaRepository.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public List<PostEntity> findAllByOrderByViewsDesc() {
		return postJpaRepository.findAllByOrderByViewsDesc();
	}
}
