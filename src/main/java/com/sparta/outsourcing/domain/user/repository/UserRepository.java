package com.sparta.outsourcing.domain.user.repository;

import com.sparta.outsourcing.domain.user.entity.UserEntity;

public interface UserRepository {

    void validateUserDuplicate(String username);

    void save(UserEntity userEntity);
}
