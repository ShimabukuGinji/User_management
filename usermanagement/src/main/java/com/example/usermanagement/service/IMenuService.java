package com.example.usermanagement.service;

import com.example.usermanagement.entity.Detail;
import com.example.usermanagement.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMenuService {
    List<Menu> findAll();

    Detail findById(int id);

    List<Menu> findKeyword(String keyword , int sort);
}
