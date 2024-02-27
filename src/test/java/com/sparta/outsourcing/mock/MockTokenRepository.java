package com.sparta.outsourcing.mock;

import com.sparta.outsourcing.domain.user.entity.TokenEntity;
import com.sparta.outsourcing.domain.user.model.Token;
import com.sparta.outsourcing.domain.user.repository.token.TokenRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.test.util.ReflectionTestUtils;

public class MockTokenRepository implements TokenRepository {

    private final Map<String, TokenEntity> store = new HashMap<>();

    @Override
    public Token findBy(String accessToken) {
        return store.values().stream()
            .filter(tokenEntity -> tokenEntity.getAccessToken().equals(accessToken))
            .map(Token::from)
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(String token) {
        store.put(token, new TokenEntity(token, false));
    }

    @Override
    public void update(Token token) {
        TokenEntity tokenEntity = store.get(ReflectionTestUtils.getField(token, "accessToken"));

        ReflectionTestUtils.setField(tokenEntity, "isExpired", true);
    }

    @Override
    public void validateExpired(String accesstoken) {
        TokenEntity tokenEntity = store.values().stream()
            .filter(token -> token.getAccessToken().equals(accesstoken))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
        if (tokenEntity.getIsExpired()) {
            throw new IllegalArgumentException();
        }
    }
}
