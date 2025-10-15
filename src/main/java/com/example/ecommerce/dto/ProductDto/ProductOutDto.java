package com.example.ecommerce.dto.ProductDto;

import lombok.Data;

@Data
public class ProductOutDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long storeId;
}
