package com.example.usermanagement.service;

import com.example.usermanagement.entity.IUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<IUser> findAll();
}
