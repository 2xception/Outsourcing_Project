package com.sparta.outsourcing.global.argumentResolver;

import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import com.sparta.outsourcing.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserInfo.class)
            && User.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String tokenValue = jwtUtil.getJwtFromRequest(request);
        String token = jwtUtil.substringToken(tokenValue);

        String userInfo = jwtUtil.getUserInfoFromToken(token);

        return userRepository.userBy(userInfo);
    }
}
