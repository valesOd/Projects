package com.vales.socialSupport.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }
}
