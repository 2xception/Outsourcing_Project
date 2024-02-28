package com.sparta.outsourcing.domain.user.dto;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import lombok.Getter;

@Getter
public class GetProfileResponseDto {

	private String nickname;
	private String photo;

	public GetProfileResponseDto(UserEntity user) {
		this.nickname = user.getNickname();
		this.photo = user.getPhoto();
	}
}
