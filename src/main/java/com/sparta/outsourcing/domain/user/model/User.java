package com.sparta.outsourcing.domain.user.model;

import com.sparta.outsourcing.domain.user.dto.ProfileRequsetDto;
import com.sparta.outsourcing.domain.user.dto.ProfileResponseDto;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(username, user.username) && Objects.equals(email,
            user.email) && Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, nickname);
    }

    public void update(ProfileRequsetDto requsetDto) {
        this.nickname = requsetDto.getNickname();
        this.photo = requsetDto.getPhoto();
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public ProfileResponseDto profileResponseDto() {
        return new ProfileResponseDto(nickname, email, photo);
    }

}
