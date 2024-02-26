package com.sparta.outsourcing.domain.post.dto;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetPostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Long views;
    private LocalDateTime createdAt;

    public GetPostResponseDto(PostEntity postEntity){
        this.postId = postEntity.getPostId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.views = postEntity.getViews();
        this.createdAt = postEntity.getCreatedAt();
    }
}
