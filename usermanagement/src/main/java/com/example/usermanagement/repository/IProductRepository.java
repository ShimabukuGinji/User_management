package com.example.usermanagement.repository;

import com.example.usermanagement.entity.InsertProduct;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.exception.NoSuchPostalCodeException;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    int insert(InsertProduct product) throws NoSuchPostalCodeException;
}
