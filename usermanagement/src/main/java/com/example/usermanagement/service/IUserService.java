package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<User> findAll();

    User findLogin(String loginId, String loginPass);
}
