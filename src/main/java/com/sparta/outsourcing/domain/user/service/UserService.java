package com.sparta.outsourcing.domain.user.service;

import com.sparta.outsourcing.domain.comment.dto.CommentResponseDto;
import com.sparta.outsourcing.domain.comment.repository.CommentRepository;
import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.follow.repository.FollowRepository;
import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.post.dto.GetPostResponseDto;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.dto.ChangePasswordRequestDto;
import com.sparta.outsourcing.domain.user.dto.GetProfileResponseDto;
import com.sparta.outsourcing.domain.user.dto.LoginRequestDto;
import com.sparta.outsourcing.domain.user.dto.ProfileRequsetDto;
import com.sparta.outsourcing.domain.user.dto.ProfileResponseDto;
import com.sparta.outsourcing.domain.user.dto.SignupRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupResponseDto;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.Token;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.domain.user.repository.token.TokenRepository;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final FollowRepository followRepository;


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

    public ProfileResponseDto getProfile(User user) {

        ProfileResponseDto profileResponseDto = user.profileResponseDto();

        profileResponseDto.setMyPosts(getPostResponseDtoListBy(user));
        profileResponseDto.setMyComments(getCommentResponseDtoListBy(user));
        profileResponseDto.setMyFollowers(getFollowerListBy(user));

        return profileResponseDto;
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

    public ProfileResponseDto getOtherProfile(Long id) {
        User user = userRepository.userById(id);

        return getProfile(user);
    }

    public List<GetPostResponseDto> getPostResponseDtoListBy(User user) {
        return postRepository
            .findByUserEntityUserId(user.toEntity().getUserId()).stream()
            .filter(Objects::nonNull)
            .map(GetPostResponseDto::new)
            .collect(Collectors.toList());
    }

    public List<CommentResponseDto> getCommentResponseDtoListBy(User user) {
        return commentRepository
            .findByUserEntityUserId(user.toEntity().getUserId()).stream()
            .filter(Objects::nonNull)
            .map(CommentResponseDto::new)
            .collect(Collectors.toList());
    }

    public List<GetProfileResponseDto> getFollowerListBy(User user) {
        return followRepository.findAllByFollowing(user.toEntity()).stream()
            .filter(Objects::nonNull)
            .map(followEntity -> new GetProfileResponseDto(followEntity.getFollower()))
            .collect(Collectors.toList());
    }

}