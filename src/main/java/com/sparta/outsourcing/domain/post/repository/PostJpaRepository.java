package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.post.model.Post;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.model.User;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

	List<PostEntity> findAllByOrderByCreatedAtDesc();

	List<PostEntity> findAllByOrderByViewsDesc();

}
