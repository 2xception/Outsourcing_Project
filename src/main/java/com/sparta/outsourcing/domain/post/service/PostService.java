package com.sparta.outsourcing.domain.post.service;

import static com.sparta.outsourcing.domain.post.service.StatusCheck.badRequest;
import static com.sparta.outsourcing.domain.post.service.StatusCheck.forBidden;
import static com.sparta.outsourcing.domain.post.service.StatusCheck.success;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.follow.repository.FollowRepository;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto2;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.postLike.repository.PostLikeRepository;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
  public List<GetPostListResponseDto2> getPostsSortedByLikes(User user) {

    List<PostLikeEntity> postLikeList = postLikeRepository.findAllByUserEntity(user.toEntity());
    if(postLikeList.isEmpty()) {
      throw new IllegalArgumentException("좋아요 표시한 게시글이 없습니다.");
    }

    List<PostEntity> postList = postLikeList.stream().map(PostLikeEntity::getPostEntity).toList();

    return postList.stream().map(PostEntity::toDto).toList();
  }

  //게시물전체조회 - 내가 팔로우한
  public List<GetPostListResponseDto2> getPostsSortedByFollow(User user) {

    List<FollowEntity> followingList = followRepository.findAllByFollower(user.toEntity());
    List<UserEntity> followingUser = followingList.stream().map(FollowEntity::getFollowing).toList();
		List<PostEntity> followingUserPost = followingUser.stream().flatMap(userEntity -> userEntity.getPostList().stream()).toList();

		if(followingList.isEmpty()) {
			throw new IllegalArgumentException("팔로우한 사용자가 없습니다.");
		}

    return followingUserPost.stream().map(PostEntity::toDto).toList();
  }

  //게시물전체조회 - 좋아요순
  public List<GetPostListResponseDto2> getPosts() {
		List<PostEntity> postList = postRepository.findAll();
		postList.sort(Comparator.comparing((PostEntity post) -> post.getPostLikeList().size()).reversed());

		if(postList.isEmpty()) {
      throw new IllegalArgumentException("게시물이 없습니다.");
    }

    return postList.stream().map(PostEntity::toDto).toList();
  }

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
		return success("작성순 전체 게시물 조회에 성공하셨습니다.",
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
		Optional<PostEntity> postEntity = postRepository.findByPostId(id);
		if(postEntity.isEmpty()){
			return badRequest("해당 id값의 게시글이 없습니다.");
		}
		Post post = Post.from(postEntity.get());
		post.viewCount();
		postRepository.update(post);
		return success("게시물 조회에 성공하셨습니다.", post.ResponseDto());
	}

	@Transactional
	public ResponseEntity<ResponseDto<?>> updatePost(Long id, User user,
		PostRequestDto requestDto) {
		Optional<PostEntity> postEntity = postRepository.findByPostId(id);
		if(postEntity.isEmpty()){
			return badRequest("해당 id값의 게시글이 없습니다.");
		}
		Post post = Post.from(postEntity.get());
		if (!Objects.equals(post.getUserEntity().getUserId(), user.toEntity().getUserId())) {
			return forBidden("이 게시물을 수정하실 권한이 없습니다.");
		}
		post.update(requestDto);
		postRepository.update(post);
		return success("게시물 수정에 성공하셨습니다.", post.ResponseDto());
	}

	@Transactional
	public ResponseEntity<ResponseDto<?>> deletePost(Long id, User user) {
		Optional<PostEntity> postEntity = postRepository.findByPostId(id);
		if(postEntity.isEmpty()){
			return badRequest("해당 id값의 게시글이 없습니다.");
		}
		Post post = Post.from(postEntity.get());
		if (!Objects.equals(post.getUserEntity().getUserId(), user.toEntity().getUserId())) {
			return forBidden("이 게시물을 삭제하실 권한이 없습니다.");
		}
		postRepository.delete(post);
		return success("게시물 삭제에 성공하셨습니다.", "");
	}


}
