package com.example.ecommerce.dto.OrderDto;

import lombok.Data;

@Data
public class OrderInDto {
    private double totalPrice;
    private Long productVariantId;
    private Long userId;
}
