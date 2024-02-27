package com.sparta.outsourcing.domain.user.repository;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.Optional;

public interface UserRepository {

    void validateUserDuplicate(String username);

    void save(UserEntity userEntity);

    User userBy(String username);

    void update(User user);

    User userById(Long id);

    Optional<UserEntity> finById(Long id);
}
