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
	private LocalDateTime createdAt;
}
