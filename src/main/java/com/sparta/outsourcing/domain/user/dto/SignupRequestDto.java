package com.sparta.outsourcing.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수로 입력해야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    private String password;

    @Email
    @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
    private String email;
}