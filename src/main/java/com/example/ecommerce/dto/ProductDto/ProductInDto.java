package com.example.ecommerce.dto.ProductDto;

import lombok.Data;

@Data
public class ProductInDto {
    private String name;
    private String description;
    private double price;
    private Long storeId;
}
