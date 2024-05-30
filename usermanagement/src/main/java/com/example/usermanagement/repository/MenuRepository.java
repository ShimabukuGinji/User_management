package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Menu;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuRepository implements IMenuRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Menu> findAll() {
        return jdbcTemplate.query("SELECT p.id, product_id , p.name, price, c.name AS category_name FROM products p JOIN categories c ON p.category_id = c.id ORDER BY p.product_id;",
            new DataClassRowMapper<>(Menu.class));
    }

    @Override
    public List<Menu> findKeyword(String keyword) {
        var param = new MapSqlParameterSource();
        param.addValue("keyword", "%" + keyword + "%");
        return jdbcTemplate.query("SELECT p.id, product_id , p.name, price, c.name AS category_name FROM products p JOIN categories c ON p.category_id = c.id WHERE p.name LIKE :keyword ORDER BY p.product_id;",
                new DataClassRowMapper<>(Menu.class));
    }
}
