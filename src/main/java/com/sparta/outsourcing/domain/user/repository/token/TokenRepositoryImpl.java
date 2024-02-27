package com.sparta.outsourcing.domain.user.repository.token;

import com.sparta.outsourcing.domain.user.entity.TokenEntity;
import com.sparta.outsourcing.domain.user.model.Token;
import io.jsonwebtoken.JwtException;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;


    @Override
    public Token findBy(String accessToken) {
        return Token.from(tokenJpaRepository.findById(accessToken).orElseThrow(
            () -> new NoSuchElementException("로그인되지 않은 토큰입니다.")
        ));
    }

    @Override
    public void save(String token) {
        tokenJpaRepository.save(new TokenEntity(token, false));
    }

    @Override
    public void update(Token token) {
        tokenJpaRepository.saveAndFlush(token.toEntity());
    }

    @Override
    public void validateExpired(String bearerToken) {
        TokenEntity token = tokenJpaRepository.findById(bearerToken).orElseThrow(
            () -> new JwtException("유효한 토큰이 아닙니다."));
        if (token.getIsExpired()) {
            throw new JwtException("이미 로그아웃 처리된 토큰입니다. 다시 로그인하세요.");
        }
    }
}
