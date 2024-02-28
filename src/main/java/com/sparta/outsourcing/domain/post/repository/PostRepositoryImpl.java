package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

	private final PostJpaRepository postJpaRepository;

	@Override
	public List<PostEntity> findAll() {
		return postJpaRepository.findAll();
	}

	@Override
	public Optional<PostEntity> finById(Long postId) {
		return postJpaRepository.findById(postId);
	}

	@Override
	public Post findByPostId(Long id) {
		return Post.from(postJpaRepository.findById(id).orElseThrow(() ->
			new EntityNotFoundException("해당 id값의 게시글이 없습니다.")
		));
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

	@Override
	public List<PostEntity> findByUserEntityUserId(Long userId) {
		return postJpaRepository.findByUserEntityUserId(userId);
	}
}
