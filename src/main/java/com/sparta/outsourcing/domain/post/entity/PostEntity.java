package com.sparta.outsourcing.domain.post.entity;

import com.sparta.outsourcing.domain.post.dto.GetPostListResponseDto;
import com.sparta.outsourcing.domain.postLike.entity.PostLikeEntity;
import com.sparta.outsourcing.domain.user.entity.UserEntity;
import jakarta.persistence.Column;
import com.sparta.outsourcing.domain.post.dto.PostRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.EntityListeners;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_POST")
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

  @CreatedDate
  @Column(updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private UserEntity userEntity;

  @OneToMany(mappedBy = "postEntity")
  private List<PostLikeEntity> postLikeList = new ArrayList<>();

  public PostEntity(Long postId, String title, String content, Long views, LocalDateTime createdAt,
      UserEntity userEntity, List<PostLikeEntity> postLikeList) {
    PostId = postId;
    this.title = title;
    this.content = content;
    this.views = views;
    this.createdAt = createdAt;
    this.userEntity = userEntity;
    this.postLikeList = postLikeList;
  }

  public PostEntity(Long postId, String title, String content, Long views, LocalDateTime createdAt,
      UserEntity userEntity) {

  }

  public GetPostListResponseDto toDto() {
    return new GetPostListResponseDto(this.title, this.content, this.postLikeList.size(), this.views, this.userEntity.getNickname());
  }

	public PostEntity(PostRequestDto requestDto, UserEntity entity) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.views = 0L;
		this.userEntity = entity;
	}
}
