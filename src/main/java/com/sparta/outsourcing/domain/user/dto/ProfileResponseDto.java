package com.sparta.outsourcing.domain.user.dto;


import lombok.Getter;

@Getter
public class ProfileResponseDto {

	private String nickname;
	private String email;
	private String photo;

	public ProfileResponseDto(String nickname, String email, String photo) {
		this.nickname = nickname;
		this.email = email;
		this.photo = photo;
	}
}