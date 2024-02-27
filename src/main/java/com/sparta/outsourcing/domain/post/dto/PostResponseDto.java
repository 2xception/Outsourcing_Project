package com.sparta.outsourcing.domain.post.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {

	private Long postId;
	private String title;
	private String content;
	private String nickname;
	private Long views;
	private LocalDateTime createdAt;
}
