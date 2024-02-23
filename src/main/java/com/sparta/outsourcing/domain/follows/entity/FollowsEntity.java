package com.sparta.outsourcing.domain.follows.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FollowsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FollowsId;
}
