package com.example.usermanagement.repository;

import com.example.usermanagement.entity.InsertProduct;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.exception.NoSuchPostalCodeException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductRepository implements IProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id",
            new DataClassRowMapper<>(Product.class));
    }

    @Override
    public int insert(InsertProduct product) throws NoSuchPostalCodeException {
        var param = new MapSqlParameterSource();
        param.addValue("product_id", product.product_id());
        param.addValue("name", product.name());
        param.addValue("price", product.price());
        param.addValue("category_id", product.category_id());
        param.addValue("description", product.description());
        try {
            return jdbcTemplate.update(
                    "INSERT INTO products (product_id, category_id, name, price, description) VALUES(:product_id, :category_id, :name, :price, :description)"
                    , param);
        } catch (Exception e) {
            throw new NoSuchPostalCodeException();
        }
    }
}
