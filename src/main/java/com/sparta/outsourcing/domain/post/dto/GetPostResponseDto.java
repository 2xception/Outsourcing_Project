package com.sparta.outsourcing.domain.post.dto;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostResponseDto {

	private Long postId;
	private String title;
	private String content;
	private String nickname;
	private Long views;
	private Integer likes;
	private LocalDateTime createdAt;

	public GetPostResponseDto(PostEntity postEntity) {
		this.postId = postEntity.getPostId();
		this.title = postEntity.getTitle();
		this.content = postEntity.getContent();
		this.nickname = postEntity.getUserEntity().getNickname();
		this.views = postEntity.getViews();
		this.createdAt = postEntity.getCreatedAt();
		this.likes = postEntity.getPostLikeList().size();
	}
}
