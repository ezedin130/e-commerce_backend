package com.example.ecommerce.dto.OrderItemDto;

import lombok.Data;

@Data
public class OrderItemInDto {
    private Integer quantity;
    private double unitPrice;
    private double subTotalPrice;
    private Long orderId;
}
