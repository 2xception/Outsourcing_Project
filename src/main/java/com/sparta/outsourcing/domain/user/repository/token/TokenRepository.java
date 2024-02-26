package com.sparta.outsourcing.domain.user.repository.token;

import com.sparta.outsourcing.domain.user.model.Token;

public interface TokenRepository {

    Token findBy(String accessToken);

    void save(String token);

    void update(Token token);
}
