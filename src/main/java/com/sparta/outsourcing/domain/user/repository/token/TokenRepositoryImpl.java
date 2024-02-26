package com.sparta.outsourcing.domain.user.repository.token;

import com.sparta.outsourcing.domain.user.entity.TokenEntity;
import com.sparta.outsourcing.domain.user.model.Token;
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
}
