package com.sparta.outsourcing.domain.post.entity;

import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(name = "POST")
public class PostEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long PostId;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "views")
  private Long views;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @OneToMany(mappedBy = "postEntity")
  private List<PostLikeEntity> postLikeList = new ArrayList<>();

  public GetPostListResponseDto toDto() {
    return new GetPostListResponseDto(this.title, this.content, this.postLikeList.size(), this.views, this.userEntity.getNickname());
  }
}
