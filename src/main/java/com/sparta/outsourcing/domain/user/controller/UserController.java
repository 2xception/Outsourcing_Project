package com.sparta.outsourcing.domain.user.controller;

import com.sparta.outsourcing.domain.user.dto.LoginRequestDto;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PatchMapping("/v1/users/logout")
    public ResponseEntity<ResponseDto<Void>> logout(
        @RequestHeader(value = "Authorization") String accessToken) {

        userService.logout(accessToken);

        return ResponseEntity.ok()
            .body(ResponseDto.<Void>builder()
                .message("로그아웃 성공")
                .build());
    }

}