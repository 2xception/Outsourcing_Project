package com.sparta.outsourcing.domain.user.repository.token;

import com.sparta.outsourcing.domain.user.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, String> {
    
}
