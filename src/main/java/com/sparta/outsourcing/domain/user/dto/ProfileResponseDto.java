package com.sparta.outsourcing.domain.user.dto;


import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class ProfileResponseDto {

	private String nickname;
	private String email;
	private String photo;
	private List<GetPostResponseDto> myPosts;

	public ProfileResponseDto(String nickname, String email, String photo) {
		this.nickname = nickname;
		this.email = email;
		this.photo = photo;
	}

	public void setMyPosts(List<GetPostResponseDto> myPosts) {
		this.myPosts = myPosts;
	}
}
