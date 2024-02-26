package com.sparta.outsourcing.domain.post.dto;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Long views;
    private LocalDateTime createdAt;
}
