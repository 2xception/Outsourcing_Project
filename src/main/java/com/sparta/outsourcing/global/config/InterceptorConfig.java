package com.sparta.outsourcing.global.config;

import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.global.interceptor.AuthenticationInterceptor;
import com.sparta.outsourcing.global.interceptor.LogInterceptor;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
            .order(1)    // 적용할 필터 순서 설정
            .addPathPatterns("/**")
            .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**"); // 인터셉터에서 제외할 패턴

        registry.addInterceptor(new AuthenticationInterceptor(jwtUtil, userRepository))
            .order(2)    // 적용할 필터 순서 설정
            .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**", "/api/users/**",
                "/api/users/signup", "/api/users/login", "/api/posts") // 인터셉터에서 제외할 패턴
            .addPathPatterns("/**");

    }
}