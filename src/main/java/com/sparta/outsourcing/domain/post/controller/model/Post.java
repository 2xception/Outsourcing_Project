package com.sparta.outsourcing.domain.post.controller.model;

import com.sparta.outsourcing.domain.post.entity.PostEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Post {

    private Long postId;
    private String title;
    private String content;
    private Long views;
    private LocalDateTime createdAt;
    private UserEntity userEntity;

    public static Post from(final PostEntity postEntity){
        return new Post(
                postEntity.getPostId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getViews(),
                postEntity.getCreatedAt(),
                postEntity.getUserEntity()
        );
    }

    public PostEntity toEntity(){
        return new PostEntity(
                postId,
                title,
                content,
                views,
                createdAt,
                userEntity
        );
    }

    public boolean validateById(Long postId) {
        return this.postId.equals(postId);
    }

}
