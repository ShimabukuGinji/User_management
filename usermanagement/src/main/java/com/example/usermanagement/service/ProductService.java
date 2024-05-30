package com.example.usermanagement.service;

import com.example.usermanagement.entity.InsertProduct;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.exception.NoSuchPostalCodeException;
import com.example.usermanagement.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        var products = productRepository.findAll();
        return products;
    }

    @Override
    public int insert(InsertProduct product) throws NoSuchPostalCodeException {
        var result = productRepository.insert(product);
        return result;
    }
}
