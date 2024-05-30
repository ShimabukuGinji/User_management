package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Categories;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesRepository implements ICategoriesRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Categories> findAll() {
        return jdbcTemplate.query("SELECT * FROM categories ORDER BY id",
            new DataClassRowMapper<>(Categories.class));
    }
}
