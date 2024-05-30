package com.example.usermanagement.service;

import com.example.usermanagement.entity.Categories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoriesService {

    List<Categories> findAll();


}
