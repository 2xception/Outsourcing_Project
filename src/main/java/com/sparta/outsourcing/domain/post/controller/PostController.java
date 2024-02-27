package com.sparta.outsourcing.domain.post.controller;

import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaAuditing
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/api/posts")
	@Operation(summary = "Post 작성")
	public ResponseEntity<ResponseDto<?>> createPost(
		@UserInfo User user,
		@RequestBody @Valid PostRequestDto requestDto) {

		return postService.createPost(user, requestDto);
	}

	@GetMapping("/api/posts")
	@Operation(summary = "Post 작성순 전체 조회")
	public ResponseEntity<ResponseDto<?>> getOrderDatePost() {

		return postService.getOrderDatePost();
	}

	@GetMapping("/api/posts/order-views")
	@Operation(summary = "Post 조회순 전체 조회")
	public ResponseEntity<ResponseDto<?>> getOrderViewPost() {
		return postService.getOrderViewPost();
	}

	@GetMapping("/api/posts/{id}")
	@Operation(summary = "특정 Post 조회")
	public ResponseEntity<ResponseDto<?>> getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@PatchMapping("/api/posts/{id}")
	@Operation(summary = "Post 수정")
	public ResponseEntity<ResponseDto<?>> updatePost(
		@PathVariable Long id,
		@UserInfo User user,
		@RequestBody @Valid PostRequestDto requestDto) {

		return postService.updatePost(id, user, requestDto);
	}

	@DeleteMapping("/api/posts/{id}")
	@Operation(summary = "Post 삭제")
	public ResponseEntity<ResponseDto<?>> deletePost(
		@PathVariable Long id,
		@UserInfo User user) {

		return postService.deletePost(id, user);
	}

}
