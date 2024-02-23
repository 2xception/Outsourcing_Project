package com.sparta.outsourcing.domain.user.dto;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class SignupResponseDto {

    private final Long id;
    private final String userName;

    public SignupResponseDto(UserEntity userEntity) {
        this.id = userEntity.getUserId();
        this.userName = userEntity.getUsername();
    }
}
