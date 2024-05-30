package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Menu;
import com.example.usermanagement.entity.User;

import java.util.List;

public interface IMenuRepository {
    List<Menu> findAll();
    List<Menu> findKeyword(String keyword);

}
