package com.example.ecommerce.dto.OrderDto;

import lombok.Data;

@Data
public class OrderInDto {
    private String orderStatus;
    private String paymentStatus;
    private double totalPrice;
    private Long productVariantId;
}
