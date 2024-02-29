package com.sparta.outsourcing.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.sparta.outsourcing.domain.comment.repository.CommentRepository;
import com.sparta.outsourcing.domain.follow.repository.FollowRepository;
import com.sparta.outsourcing.domain.post.repository.PostRepository;
import com.sparta.outsourcing.domain.user.dto.LoginRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupRequestDto;
import com.sparta.outsourcing.domain.user.dto.SignupResponseDto;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.domain.user.repository.token.TokenRepository;
import com.sparta.outsourcing.domain.user.service.UserService;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import com.sparta.outsourcing.user.fixture.TestUser;
import com.sparta.outsourcing.user.mock.MockPasswordEncoder;
import com.sparta.outsourcing.user.mock.MockTokenRepository;
import com.sparta.outsourcing.user.mock.MockUserRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserServiceTest {

    private static final Long USER_ID = 1L;
    private static final String USERNAME = "sjh";
    private static final String PASSWORD = "12345678";
    private static final String EMAIL = "sjh@gmail.com";
    private static final String NONE_USERNAME = "sjh1";
    private static final String WRONG_PASSWORD = "123456789";
    private static final String TEST_TOKEN = "testToken";
    @Mock
    JwtUtil jwtUtil;
    @Mock
    PostRepository postRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    FollowRepository followRepository;

    private UserService userService;

    @BeforeEach
    void init() {
        UserRepository userRepository = new MockUserRepository();
        TokenRepository tokenRepository = new MockTokenRepository();
        PasswordEncoder passwordEncoder = new MockPasswordEncoder();

        userService = new UserService(userRepository, postRepository, commentRepository,
            tokenRepository, passwordEncoder, jwtUtil, followRepository);
    }

    private <T> void setDto(T dto, String username, String password, String email) {
        ReflectionTestUtils.setField(dto, "username", username);
        ReflectionTestUtils.setField(dto, "password", password);
        ReflectionTestUtils.setField(dto, "email", email);
    }

    private <T> void setDto(T dto, String username, String password) {
        ReflectionTestUtils.setField(dto, "username", username);
        ReflectionTestUtils.setField(dto, "password", password);
    }

    @Nested
    class signup_회원가입_테스트 {

        @Test
        void 회원가입_성공() {
            //given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            setDto(signupRequestDto, USERNAME, PASSWORD, EMAIL);

            //when
            SignupResponseDto signupResponseDto = userService.signup(signupRequestDto);

            //then
            assertEquals(USER_ID, signupResponseDto.getId());
            assertEquals(USERNAME, signupResponseDto.getUserName());
            assertEquals(PASSWORD, signupRequestDto.getPassword());
        }

        @Test
        void 중복유저_회원가입_실패() {
            //given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            setDto(signupRequestDto, USERNAME, PASSWORD, EMAIL);

            userService.signup(signupRequestDto);

            //when - then
            assertThrows(DuplicateKeyException.class, () -> userService.signup(signupRequestDto));
        }

    }

    @Nested
    class login_로그인_테스트 {

        @Test
        void 로그인_성공() {
            //given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            setDto(signupRequestDto, USERNAME, PASSWORD, EMAIL);
            userService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            setDto(loginRequestDto, USERNAME, PASSWORD);

            given(jwtUtil.createToken(USERNAME)).willReturn(TEST_TOKEN);

            //when
            String token = userService.login(loginRequestDto);

            //then
            assertEquals(new TestUser().createToken(jwtUtil), token);
        }

        @Test
        void 사용자_조회_실패() {
            //given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            setDto(signupRequestDto, USERNAME, PASSWORD, EMAIL);
            userService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            setDto(loginRequestDto, NONE_USERNAME, PASSWORD);

            //when-then
            assertThrows(NoSuchElementException.class, () -> userService.login(loginRequestDto));
        }

        @Test
        void 패스워드_불일치_로그인_실패() {
            //given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            setDto(signupRequestDto, USERNAME, PASSWORD, EMAIL);
            userService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            setDto(loginRequestDto, USERNAME, WRONG_PASSWORD);

            assertThrows(BadCredentialsException.class, () -> userService.login(loginRequestDto));
        }
    }

    @Nested
    class logout_로그아웃_테스트 {

        @Test
        void 로그아웃_성공() {
            //given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            setDto(signupRequestDto, USERNAME, PASSWORD, EMAIL);
            userService.signup(signupRequestDto);

            LoginRequestDto loginRequestDto = new LoginRequestDto();
            setDto(loginRequestDto, USERNAME, PASSWORD);

            given(jwtUtil.createToken(USERNAME)).willReturn(TEST_TOKEN);
            String token = userService.login(loginRequestDto);

            //when
            userService.logout(token);
        }
    }
}
