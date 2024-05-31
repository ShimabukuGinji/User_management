package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Categories;

import java.util.List;

public interface ICategoriesRepository {
    List<Categories> findAll();

    String findByName(String name);
}
