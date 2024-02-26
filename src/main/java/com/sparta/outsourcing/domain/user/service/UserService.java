package com.sparta.outsourcing.domain.user.service;

import com.sparta.outsourcing.domain.user.dto.LoginRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupResponseDto;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.Token;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.domain.user.repository.token.TokenRepository;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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
        String token = user.createToken(jwtUtil);

        tokenRepository.save(token);

        return token;
    }

    @Transactional
    public void logout(String accessToken) {
        Token token = tokenRepository.findBy(accessToken);
        token.expireToken();

        tokenRepository.update(token);
    }
}
