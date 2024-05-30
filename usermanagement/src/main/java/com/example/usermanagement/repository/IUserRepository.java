package com.example.usermanagement.repository;

import com.example.usermanagement.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();

    User findLogin(String loginId, String loginPass);
}
