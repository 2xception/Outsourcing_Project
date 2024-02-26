package com.sparta.outsourcing.domain.follow.entity;

import com.sparta.outsourcing.domain.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "TB_FOLLOW")
@Getter
@Table(name = "FOLLOW")
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FollowsId;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private UserEntity following;

    public FollowEntity(UserEntity entity, UserEntity findUser) {
        this.follower = entity;
        this.following = findUser;
    }
}
