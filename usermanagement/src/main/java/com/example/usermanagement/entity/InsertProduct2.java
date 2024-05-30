package com.example.usermanagement.entity;

public record InsertProduct2(String product_id, int category_id, Product name, int price, String image_path, String description, String created_at, String updated_at) {
}