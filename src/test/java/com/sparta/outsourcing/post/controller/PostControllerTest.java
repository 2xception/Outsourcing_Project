package com.sparta.outsourcing.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.outsourcing.domain.post.controller.PostController;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import com.sparta.outsourcing.domain.post.service.PostService;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
@ActiveProfiles("test")
class PostControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	PostService postService;

	@MockBean
	JwtUtil jwtUtil;

	@Test
	@DisplayName("할일 카드 만들기 페이지 테스트")
	@WithMockUser(username = "testUser", roles = "USER")
	void createSchedulePage() throws Exception {
		// given
		PostRequestDto requestDto = new PostRequestDto("제목", "내용");
		String requestBody = objectMapper.writeValueAsString(requestDto);

		// when-then
		mvc.perform(post("/api/posts")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk());
	}
}
