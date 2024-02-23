package com.sparta.outsourcing.domain.follows.repository;

import com.sparta.outsourcing.domain.follows.entity.FollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsJpaRepository extends JpaRepository<FollowsEntity, Long> {

}
