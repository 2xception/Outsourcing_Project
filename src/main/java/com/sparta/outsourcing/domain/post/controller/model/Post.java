package com.sparta.outsourcing.domain.post.controller.model;

import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.access.AccessDeniedException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	private Long postId;
	private String title;
	private String content;
	private Long views;
	private LocalDateTime createdAt;
	private UserEntity userEntity;

	public static Post from(final PostEntity postEntity) {
		return new Post(
			postEntity.getPostId(),
			postEntity.getTitle(),
			postEntity.getContent(),
			postEntity.getViews(),
			postEntity.getCreatedAt(),
			postEntity.getUserEntity()
		);
	}

	public PostEntity toEntity() {
		return new PostEntity(
			postId,
			title,
			content,
			views,
			createdAt,
			userEntity
		);
	}

	public GetPostResponseDto ResponseDto() {
		return new GetPostResponseDto(
			postId,
			title,
			content,
			userEntity.getNickname(),
			views,
			createdAt
		);
	}


	public void viewCount() {
		views++;
	}

	public void update(PostRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
	}

	public void checkForbidden(Long id){
		if(!Objects.equals(userEntity.getUserId(), id)){
			throw new AccessDeniedException("해당 게시물을 수정/삭제 하실 권한이 없습니다.");
		}
	}

}
