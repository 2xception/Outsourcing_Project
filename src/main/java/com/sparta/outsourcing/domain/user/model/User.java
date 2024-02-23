package com.sparta.outsourcing.domain.user.model;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class User {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String photo;

    public static User from(final UserEntity userEntity) {
        return new User(
            userEntity.getUserId(),
            userEntity.getUsername(),
            userEntity.getPassword(),
            userEntity.getEmail(),
            userEntity.getNickname(),
            userEntity.getPhoto()
        );
    }

    public UserEntity toEntity() {
        return new UserEntity(
            userId,
            username,
            password,
            email,
            nickname,
            photo
        );
    }


    public void validatePassword(String password, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new BadCredentialsException("패스워드를 잘못 입력하였습니다.");
        }
    }

    public String createToken(JwtUtil jwtUtil) {
        return jwtUtil.createToken(username);
    }
}
