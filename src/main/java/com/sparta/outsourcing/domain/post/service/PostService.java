package com.sparta.outsourcing.domain.post.service;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public GetPostResponseDto createPost(User user, PostRequestDto requestDto) {
		PostEntity postEntity = new PostEntity(requestDto, user.toEntity());
		postRepository.save(postEntity);
		return Post.from(postEntity).ResponseDto();
	}

	public List<GetPostListResponseDto> getOrderDatePost() {
		List<PostEntity> posts = postRepository.findAllByOrderByCreatedAtDesc();
		return posts.stream().map(GetPostListResponseDto::new).toList();
	}

	public List<GetPostListResponseDto> getOrderViewPost() {
		List<PostEntity> posts = postRepository.findAllByOrderByViewsDesc();
		return posts.stream().map(GetPostListResponseDto::new).toList();
	}

	@Transactional
	public GetPostResponseDto getPost(Long id) {
		Post post = postRepository.findByPostId(id);
		post.viewCount();
		postRepository.update(post);
		return post.ResponseDto();
	}

	@Transactional
	public GetPostResponseDto updatePost(Long id, User user,
		PostRequestDto requestDto) {
		Post post = postRepository.findByPostId(id);
		post.checkForbidden(user.toEntity().getUserId());
		post.update(requestDto);
		postRepository.update(post);
		return post.ResponseDto();
	}

	@Transactional
	public void deletePost(Long id, User user) {
		Post post = postRepository.findByPostId(id);
		post.checkForbidden(user.toEntity().getUserId());
		postRepository.delete(post);
	}

}
