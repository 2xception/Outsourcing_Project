package com.sparta.outsourcing.domain.user.model;

import com.sparta.outsourcing.domain.user.entity.TokenEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Token {

    private String accessToken;
    private Boolean isExpired;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Token from(final TokenEntity tokenEntity) {
        return new Token(
            tokenEntity.getAccessToken(),
            tokenEntity.getIsExpired(),
            tokenEntity.getCreatedAt(),
            tokenEntity.getModifiedAt()
        );
    }

    public TokenEntity toEntity() {
        return new TokenEntity(
            accessToken,
            isExpired
        );
    }

    public void expireToken() {
        isExpired = true;
    }
}
