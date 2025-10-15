package com.example.ecommerce.dto.OrderDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderOutDto {
    private Long id;
    private String orderStatus;
    private LocalDate orderDate;
    private double totalPrice;
}
