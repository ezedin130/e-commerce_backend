package com.example.ecommerce.dto.ProductVariantDto;

import lombok.Data;

@Data
public class ProductVariantOutDto {
    private Long id;
    private String size;
    private String color;
    private double additionalPrice;
    private double discount;
    private Long productId;
}
