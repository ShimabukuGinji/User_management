package com.example.usermanagement.service;

import com.example.usermanagement.entity.IUser;
import com.example.usermanagement.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public List<IUser> findAll(){
        var users = userRepository.findAll();
        return users;
    }
}
