package com.sparta.outsourcing.domain.user.controller;

import com.sparta.outsourcing.domain.user.dto.LoginRequestDto;
import com.sparta.outsourcing.domain.user.dto.ProfileResponseDto;
import com.sparta.outsourcing.domain.user.dto.SignupRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupResponseDto;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.service.UserService;
import com.sparta.outsourcing.global.argumentResolver.UserInfo;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // test
    @PostMapping("/api/test")
    @Operation(summary = "jwt 유저 인증 테스트 api")
    public ResponseEntity<ResponseDto<Void>> test(
        @UserInfo User user) {
        return ResponseEntity.ok()
            .body(ResponseDto.<Void>builder()
                .message(user.toEntity().getUsername())
                .build());
    }

    @PostMapping("/api/users/signup")
    @Operation(summary = "회원 가입 API")
    public ResponseEntity<ResponseDto<SignupResponseDto>> signup(
        @RequestBody @Valid SignupRequestDto requestDto) {
        return ResponseEntity.ok()
            .body(ResponseDto.<SignupResponseDto>builder()
                .message("회원 가입 성공")
                .data(userService.signup(requestDto))
                .build());
    }

    @PostMapping("/api/users/login")
    @Operation(summary = "로그인 API")
    public ResponseEntity<ResponseDto<Void>> login(@RequestBody LoginRequestDto requestDto) {

        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, userService.login(requestDto))
            .body(ResponseDto.<Void>builder()
                .message("로그인 성공")
                .build());
    }

    @GetMapping("/api/users")
    @Operation(summary = "프로필 조회 API")
    public ResponseEntity<ResponseDto<ProfileResponseDto>> getProfile(@UserInfo User user) {
        return ResponseEntity.ok()
            .body(ResponseDto.<ProfileResponseDto>builder()
                .message("조회 성공")
                .data(userService.getProfile(user))
                .build());
    }
}