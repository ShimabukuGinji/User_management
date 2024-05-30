package com.example.usermanagement.service;

import com.example.usermanagement.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMenuService {
    List<Menu> findAll();

    List<Menu> findKeyword(String keyword);
}
