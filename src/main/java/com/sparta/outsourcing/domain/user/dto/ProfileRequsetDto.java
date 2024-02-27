package com.sparta.outsourcing.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProfileRequsetDto {

	@NotBlank(message = "변경하고자 하는 닉네임을 입력하세요.")
	private String nickname;
	private String photo;
}
