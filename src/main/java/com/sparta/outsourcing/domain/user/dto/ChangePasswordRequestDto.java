package com.sparta.outsourcing.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangePasswordRequestDto {

	@NotBlank(message = "현재 비밀번호를 입력하세요.")
	private String existingPassword;
	@NotBlank(message = "변경하고자 하는 비밀번호를 입력하세요.")
	private String newPassword;

}
