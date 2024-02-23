package com.sparta.outsourcing.domain.user.controller;

import com.sparta.outsourcing.domain.user.dto.SignupRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupResponseDto;
import com.sparta.outsourcing.domain.user.service.UserService;
import com.sparta.outsourcing.global.commonDto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

}