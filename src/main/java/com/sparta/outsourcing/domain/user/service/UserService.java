package com.sparta.outsourcing.domain.user.service;

import com.sparta.outsourcing.domain.user.dto.ChangePasswordRequestDto;
import com.sparta.outsourcing.domain.user.dto.LoginRequestDto;
import com.sparta.outsourcing.domain.user.dto.ProfileRequsetDto;
import com.sparta.outsourcing.domain.user.dto.ProfileResponseDto;
import com.sparta.outsourcing.domain.user.dto.SignupRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupResponseDto;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String nickname = username;
        userRepository.validateUserDuplicate(username);

        UserEntity userEntity = new UserEntity(username, password, email, nickname);
        userRepository.save(userEntity);

        return new SignupResponseDto(userEntity);
    }

    public String login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.userBy(username);
        user.validatePassword(password, passwordEncoder);

        return user.createToken(jwtUtil);
    }

    public ProfileResponseDto getProfile(User user) {
        return user.profileResponseDto();
    }

    @Transactional
    public ProfileResponseDto updateProfile(ProfileRequsetDto requsetDto, User user) {
        user.update(requsetDto);
        userRepository.update(user);

        return user.profileResponseDto();
    }

    @Transactional
    public ProfileResponseDto changePassword(ChangePasswordRequestDto requestDto, User user) {
        String existingPassword = requestDto.getExistingPassword();
        String newPassword = requestDto.getNewPassword();

        user.validatePassword(existingPassword, passwordEncoder);
        user.changePassword(passwordEncoder.encode(newPassword));

        userRepository.update(user);

        return user.profileResponseDto();
    }
}
