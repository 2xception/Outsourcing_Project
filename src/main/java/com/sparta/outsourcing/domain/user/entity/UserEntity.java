package com.sparta.outsourcing.domain.user.entity;

import com.sparta.outsourcing.domain.follow.entity.FollowEntity;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_USER")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = true)
    private String photo;

    @OneToMany(mappedBy = "userEntity")
    private List<PostEntity> postList = new ArrayList<>();

    @OneToMany(mappedBy = "following")
    private List<FollowEntity> followingList = new ArrayList<>();

    public UserEntity(String username, String password, String email, String nickname,
        String photo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.photo = photo;
    }

    public UserEntity(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public UserEntity(Long userId, String username, String password, String email, String nickname,
        String photo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.photo = photo;
    }
}
