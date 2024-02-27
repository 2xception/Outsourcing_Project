package com.sparta.outsourcing.user.mock;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import com.sparta.outsourcing.domain.user.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.util.ReflectionTestUtils;

public class MockUserRepository implements UserRepository {

    private final Map<Long, UserEntity> store = new HashMap<>();
    private Long autoGeneratedId = 0L;

    @Override
    public void validateUserDuplicate(String username) {
        Optional<User> user = store.values().stream()
            .filter(userEntity -> userEntity.getUsername().equals(username))
            .map(User::from)
            .findAny();

        if (user.isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }

    @Override
    public void save(UserEntity userEntity) {
        store.put(++autoGeneratedId, userEntity);
        ReflectionTestUtils.setField(userEntity, "userId", autoGeneratedId);
    }

    @Override
    public User userBy(String userName) {
        return store.values().stream()
            .filter(userEntity -> userEntity.getUsername().equals(userName))
            .map(User::from)
            .findAny()
            .orElseThrow(NoSuchElementException::new);
    }
}