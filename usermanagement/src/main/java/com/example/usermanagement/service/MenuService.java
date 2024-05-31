package com.example.usermanagement.service;

import com.example.usermanagement.entity.Detail;
import com.example.usermanagement.entity.Menu;
import com.example.usermanagement.repository.IMenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService implements IMenuService {

    @Autowired
    private IMenuRepository menuRepository;

    @Override
    public List<Menu> findAll(){
        var menu = menuRepository.findAll();
        return menu;
    }

    @Override
    public Detail findById(int id){
        var menu = menuRepository.findById(id);
        return menu;
    }

    @Override
    public List<Menu> findKeyword(String keyword , int sort){
        var menu = menuRepository.findKeyword(keyword, sort);
        return menu;
    }
}
