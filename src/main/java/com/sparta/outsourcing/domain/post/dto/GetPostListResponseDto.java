package com.sparta.outsourcing.domain.post.dto;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostListResponseDto {

	private Long postId;
	private String title;
	private String content;
	private String nickname;
	private Long views;
	private Integer likes;
	private LocalDateTime createdAt;

	public GetPostListResponseDto(PostEntity postEntity) {
		this.postId = postEntity.getPostId();
		this.title = postEntity.getTitle();
		this.content =
			postEntity.getContent().length() > 20 ? postEntity.getContent().substring(0, 20)
				: postEntity.getContent();
		this.nickname = postEntity.getUserEntity().getNickname();
		this.views = postEntity.getViews();
		this.createdAt = postEntity.getCreatedAt();
	}

	public GetPostListResponseDto(String title, String content, int size, Long views,
			String nickname) {
		this.title = title;
		this.content = content;
		this.likes = size;
		this.views = views;
		this.nickname = nickname;
	}
}
