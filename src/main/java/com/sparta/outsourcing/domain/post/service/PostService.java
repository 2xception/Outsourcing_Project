package com.sparta.outsourcing.domain.post.service;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.follow.repository.FollowRepository;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.postLike.repository.PostLikeRepository;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;

	private final PostLikeRepository postLikeRepository;

	private final FollowRepository followRepository;

	//게시물전체조회 - 내가 좋아요표시
	public List<GetPostListResponseDto> getPostsSortedByLikes(User user) {

		List<PostLikeEntity> postLikeList = postLikeRepository.findAllByUserEntity(user.toEntity());
		if (postLikeList.isEmpty()) {
			throw new IllegalArgumentException("좋아요 표시한 게시글이 없습니다.");
		}

		List<PostEntity> postList = postLikeList.stream().map(PostLikeEntity::getPostEntity)
			.toList();

		return postList.stream().map(PostEntity::toDto).toList();
	}

	//게시물전체조회 - 내가 팔로우한
	public List<GetPostListResponseDto> getPostsSortedByFollow(User user) {

		List<FollowEntity> followingList = followRepository.findAllByFollower(user.toEntity());
		List<UserEntity> followingUser = followingList.stream().map(FollowEntity::getFollowing)
			.toList();
		List<PostEntity> followingUserPost = followingUser.stream()
			.flatMap(userEntity -> userEntity.getPostList().stream()).toList();

		if (followingList.isEmpty()) {
			throw new IllegalArgumentException("팔로우한 사용자가 없습니다.");
		}

		return followingUserPost.stream().map(PostEntity::toDto).toList();
	}

	//게시물전체조회 - 좋아요순
	public List<GetPostListResponseDto> getPosts() {
		List<PostEntity> postList = postRepository.findAll();
		postList.sort(
			Comparator.comparing((PostEntity post) -> post.getPostLikeList().size()).reversed());

		if (postList.isEmpty()) {
			throw new IllegalArgumentException("게시물이 없습니다.");
		}

		return postList.stream().map(PostEntity::toDto).toList();
	}

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
