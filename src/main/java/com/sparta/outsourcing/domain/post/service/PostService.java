package com.sparta.outsourcing.domain.post.service;

import com.sparta.outsourcing.domain.follow.repository.FollowRepository;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.postLike.model.PostLike;
import com.sparta.outsourcing.domain.postLike.repository.PostLikeRepository;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  private final PostLikeRepository postLikeRepository;

  private final FollowRepository followRepository;

  //게시물전체조회 - 내가 좋아요표시
  public List<GetPostListResponseDto> getPostsSortedByLikes(User user) {
    List<PostLikeEntity> postLikeList = postLikeRepository.findAllByUser(user); //user모델 안으로, 엔티티객체 서비스에서X , 엔티티로넘어오는걸 바꾸고
    if(postLikeList.isEmpty()) {
      throw new IllegalArgumentException("좋아요 표시한 게시글이 없습니다.");
    }

    List<PostEntity> postList = postLikeList.stream().map(PostLikeEntity::getPostEntity).toList();

    return postList.stream().map(PostEntity::toDto).toList();
  }

  //게시물전체조회 - 내가 팔로우한
  public List<GetPostListResponseDto> getPostsSortedByFollow(User user) {
    //팔로우한 사람 리스트 가져오기
    List<UserEntity> followingList = followRepository.findAllByUser(user);
    //팔로우한 사람의 게시물리스트 저장
    List<PostEntity> postList = followingList.stream().flatMap(userEntity -> userEntity.getPostList().stream()).toList();

    return postList.stream().map(PostEntity::toDto).toList();
  }

  //게시물전체조회 - 좋아요순
  public List<GetPostListResponseDto> getPosts() {
    List<PostEntity> postList = postRepository.findAll(Sort.by("postLikeList").descending());
    if(postList.isEmpty()) {
      throw new IllegalArgumentException("게시물이 없습니다.");
    }

    return postList.stream().map(PostEntity::toDto).toList();
  }

  //게시물 좋아요 추가

  //게시물 좋아요 삭제

  //팔로우

  //언팔로우
}
