package com.sparta.outsourcing.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostRequestDto {

    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(max=30)
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(max = 1024)
    private String content;


}
