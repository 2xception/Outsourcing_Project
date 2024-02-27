package com.sparta.outsourcing.domain.post.controller.model;

import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
