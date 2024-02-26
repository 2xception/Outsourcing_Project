package com.sparta.outsourcing.domain.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetPostListResponseDto {
    private List<GetPostResponseDto> posts;

    public GetPostListResponseDto(List<GetPostResponseDto> posts){
        this.posts = posts;
    }
}
