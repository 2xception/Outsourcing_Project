package com.sparta.outsourcing.domain.comment.service;

import com.sparta.outsourcing.domain.comment.dto.CommentRequestDto;
import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.entity.CommentEntity;
import com.sparta.outsourcing.domain.comment.repository.CommentRepository;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public CommentResponseDto addComment(long postId, CommentRequestDto request, User user) {

		PostEntity post = postRepository.findByPostId(postId).get(); //임시
		User findUser = userRepository.userBy(user.toEntity().getUsername());
		CommentEntity savedComment = commentRepository.save(
			new CommentEntity(request, Post.from(post), findUser)); //임시
		return new CommentResponseDto(savedComment);
	}
}
