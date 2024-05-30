package com.example.usermanagement.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductForm {

    @NotEmpty(message = "商品IDは必須です")
    private String productId;

    @NotEmpty(message = "商品名は必須です")
    private String name;

    @NotNull
    @Positive(message = "単価は必須です")
    private String price;

    @NotEmpty(message = "カテゴリは必須です")
    private String categoryId;

    private String description;
}
