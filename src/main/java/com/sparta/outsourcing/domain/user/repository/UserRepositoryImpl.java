package com.sparta.outsourcing.domain.user.repository;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final UserJpaRepository userJpaRepository;
    @Override
    public void validateUserDuplicate(String username){
        if (userJpaRepository.findByUsername(username).isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }

    @Override
    public void save(UserEntity userEntity) {
        userJpaRepository.save(userEntity);
    }
}
