package com.example.ecommerce.dto.OrderDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInDto {
    private String orderStatus;
    private double totalPrice;
}
