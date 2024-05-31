package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Detail;
import com.example.usermanagement.entity.Menu;

import java.util.List;

public interface IMenuRepository {
    List<Menu> findAll();

    Detail findById(int id);
    List<Menu> findKeyword(String keyword , int sort);

}
