package com.sparta.outsourcing.domain.user.repository.token;

import com.sparta.outsourcing.domain.user.model.Token;

public interface TokenRepository {

    Token findBy(String accessToken);

}
