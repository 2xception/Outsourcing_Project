package com.sparta.outsourcing.domain.postLike.entity;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import com.sparta.outsourcing.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.Getter;

@Entity
@Table(name = "TB_POST_LIKE")
@Getter
public class PostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id")
    private Long PostLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public PostLikeEntity(PostEntity post, UserEntity user) {
        this.postEntity = post;
        this.userEntity = user;
    }
}
