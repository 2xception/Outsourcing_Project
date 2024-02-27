package com.sparta.outsourcing.user.fixture;

import com.sparta.outsourcing.domain.user.model.User;

public class TestUser extends User {

    public TestUser() {
        super(
            1L,
            "sjh",
            "qwerty1234",
            "sjh@gmail.com",
            "sjh",
            null
        );
    }

}
