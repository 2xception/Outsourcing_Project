package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

	List<PostEntity> findAllByOrderByCreatedAtDesc();

	List<PostEntity> findAllByOrderByViewsDesc();

	List<PostEntity> findByUserEntityUserId(Long userId);
}
