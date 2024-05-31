package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Detail;
import com.example.usermanagement.entity.Menu;
import com.example.usermanagement.entity.Product;
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
    public Detail findById(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT p.id, product_id , p.name, price, c.name AS category_name, image_path, description FROM products p JOIN categories c ON p.category_id = c.id WHERE p.id = :id",
                param, new DataClassRowMapper<>(Detail.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Menu> findKeyword(String keyword , int sort) {
        System.out.println(sort);
        var param = new MapSqlParameterSource();
        var sql = "SELECT p.id, product_id , p.name, price, c.name AS category_name, category_id FROM products p JOIN categories c ON p.category_id = c.id WHERE p.name LIKE :key ORDER BY ";
        param.addValue("key", "%" + keyword + "%");
        switch (sort) {
            case 2 -> sql = sql + "product_id DESC";
            case 3 -> sql = sql + "category_id";
            case 4 -> sql = sql + "category_id DESC";
            case 5 -> sql = sql + "price";
            case 6 -> sql = sql + "price DESC";
            default -> sql = sql + "product_id";
        }
        return jdbcTemplate.query(sql, param, new DataClassRowMapper<>(Menu.class));
    }
}
