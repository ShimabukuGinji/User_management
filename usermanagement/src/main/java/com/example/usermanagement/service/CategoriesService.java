package com.example.usermanagement.service;

import com.example.usermanagement.entity.Categories;
import com.example.usermanagement.repository.ICategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService implements ICategoriesService {

    @Autowired
    private ICategoriesRepository categoriesRepository;
    @Override
    public List<Categories> findAll(){
        return categoriesRepository.findAll();
    }

    @Override
    public String findByName(String name){
        return categoriesRepository.findByName(name);
    }

}
