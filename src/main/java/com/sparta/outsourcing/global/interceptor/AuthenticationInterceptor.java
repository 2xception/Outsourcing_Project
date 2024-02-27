package com.sparta.outsourcing.global.interceptor;


import com.sparta.outsourcing.domain.user.repository.token.TokenRepository;
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
    private final TokenRepository tokenRepository;

    @Override
    public boolean preHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler
    )throws Exception{

        String tokenValue = jwtUtil.getJwtFromRequest(request);
        tokenRepository.validateExpired(tokenValue);

        String token = jwtUtil.substringToken(tokenValue);
        jwtUtil.validateToken(token);



        return true;
    }

}
