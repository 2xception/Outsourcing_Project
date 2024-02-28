package com.sparta.outsourcing.post.mvc;

import com.sparta.outsourcing.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class MockJwtInterceptor implements HandlerInterceptor {

	private final JwtUtil jwtUtil;

	public MockJwtInterceptor(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String fakeToken = createFakeToken();
		request.setAttribute(JwtUtil.AUTHORIZATION_HEADER, JwtUtil.BEARER_PREFIX + fakeToken);
		return true;
	}

	private String createFakeToken() {
		return "가짜토큰";
	}
}