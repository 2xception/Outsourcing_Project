package com.sparta.outsourcing.global.interceptor;


import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler
    ){

        String tokenValue = jwtUtil.getJwtFromRequest(request);
        String userInfo = jwtUtil.getUserInfoFromToken(tokenValue);

        request.setAttribute("User", userRepository.userBy(userInfo));

        return true;
    }

}
