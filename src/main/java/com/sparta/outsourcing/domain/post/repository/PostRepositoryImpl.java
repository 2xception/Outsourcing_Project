package com.sparta.outsourcing.domain.post.repository;

import com.sparta.outsourcing.domain.post.controller.model.Post;
import com.sparta.outsourcing.domain.post.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
    private final PostJpaRepository postJpaRepository;

    @Override
    public Post findByPostId(Long id){
        return Post.from(postJpaRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택하신 게시물은 존재하지 않습니다.")
                ));
    }
    @Override
    public void save(PostEntity postEntity){
        postJpaRepository.save(postEntity);
    }

    @Override
    public void delete(Post post){
        postJpaRepository.delete(post.toEntity());
    }

    @Override
    public void update(Post post){
        postJpaRepository.saveAndFlush(post.toEntity());
    }

}
