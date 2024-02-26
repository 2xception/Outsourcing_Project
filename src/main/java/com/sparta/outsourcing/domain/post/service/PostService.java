package com.sparta.outsourcing.domain.post.service;

import static com.sparta.outsourcing.domain.post.service.StatusCheck.badRequest;
import static com.sparta.outsourcing.domain.post.service.StatusCheck.forBidden;
import static com.sparta.outsourcing.domain.post.service.StatusCheck.success;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public ResponseEntity<ResponseDto<?>> createPost(User user, PostRequestDto requestDto) {
		PostEntity postEntity = new PostEntity(requestDto, user.toEntity());
		postRepository.save(postEntity);
		return success("포스트 작성에 성공하셨습니다.", Post.from(postEntity).ResponseDto());
	}

	public ResponseEntity<ResponseDto<?>> getOrderDatePost() {
		List<PostEntity> posts = postRepository.findAllByOrderByCreatedAtDesc();
		if (posts.isEmpty()) {
			return badRequest("현재 작성된 게시물이 없습니다.");
		}
		return success("조회순 전체 게시물 조회에 성공하셨습니다.",
			posts.stream().map(GetPostListResponseDto::new).toList());
	}

	public ResponseEntity<ResponseDto<?>> getOrderViewPost() {
		List<PostEntity> posts = postRepository.findAllByOrderByViewsDesc();
		if (posts.isEmpty()) {
			return badRequest("현재 작성된 게시물이 없습니다.");
		}
		return success("조회순 전체 게시물 조회에 성공하셨습니다.",
			posts.stream().map(GetPostListResponseDto::new).toList());
	}

	@Transactional
	public ResponseEntity<ResponseDto<?>> getPost(Long id) {
		Post post = postRepository.findByPostId(id);
		post.viewCount();
		postRepository.update(post);
		return success("게시물 조회에 성공하셨습니다.", post.ResponseDto());
	}

	@Transactional
	public ResponseEntity<ResponseDto<?>> updatePost(Long id, User user,
		PostRequestDto requestDto) {
		Post post = postRepository.findByPostId(id);
		if (!Objects.equals(post.getUserEntity().getUserId(), user.toEntity().getUserId())) {
			return forBidden("이 게시물을 수정하실 권한이 없습니다.");
		}
		post.update(requestDto);
		postRepository.update(post);
		return success("게시물 수정에 성공하셨습니다.", post.ResponseDto());
	}

	@Transactional
	public ResponseEntity<ResponseDto<?>> deletePost(Long id, User user) {
		Post post = postRepository.findByPostId(id);
		if (!Objects.equals(post.getUserEntity().getUserId(), user.toEntity().getUserId())) {
			return forBidden("이 게시물을 삭제하실 권한이 없습니다.");
		}
		postRepository.delete(post);
		return success("게시물 삭제에 성공하셨습니다.", "");
	}
}
