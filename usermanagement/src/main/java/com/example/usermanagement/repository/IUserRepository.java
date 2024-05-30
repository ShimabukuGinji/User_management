package com.example.usermanagement.repository;

import com.example.usermanagement.entity.IUser;

import java.util.List;

public interface IUserRepository {
    List<IUser> findAll();
}
