package com.sparta.outsourcing.domain.post.service;

import com.sparta.outsourcing.global.commonDto.ResponseDto;
import org.springframework.http.ResponseEntity;

public class StatusCheck {

	public static ResponseEntity<ResponseDto<?>> success(String msg, Object object) {
		return ResponseEntity.ok(ResponseDto.builder()
			.message(msg)
			.data(object)
			.build());
	}

	public static ResponseEntity<ResponseDto<?>> badRequest(String msg) {
		return ResponseEntity.badRequest().body(ResponseDto.builder()
			.message(msg)
			.data(null)
			.build());
	}

	static ResponseEntity<ResponseDto<?>> forBidden(String msg) {
		return ResponseEntity.status(403).body(ResponseDto.builder()
			.message(msg)
			.data(null)
			.build());
	}


}
