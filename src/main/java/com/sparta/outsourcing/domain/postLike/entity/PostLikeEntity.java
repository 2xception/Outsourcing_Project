package com.sparta.outsourcing.domain.postLike.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostLikeId;
}
