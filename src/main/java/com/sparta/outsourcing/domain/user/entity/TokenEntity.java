package com.sparta.outsourcing.domain.user.entity;

import com.sparta.outsourcing.global.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_Token")
@NoArgsConstructor
public class TokenEntity extends Timestamped {

    @Id
    private String accessToken;

    @Column(nullable = false)
    private Boolean isExpired;

    public TokenEntity(String tokenValue, boolean isExpired) {
        this.accessToken = tokenValue;
        this.isExpired = isExpired;
    }

}

