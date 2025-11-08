package com.example.ecommerce.dto.OrderDto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderOutDto {
    private Long id;
    private String orderStatus;
    private String paymentStatus;
    private LocalDate orderDate;
    private double totalPrice;
    private Long productVariantId;
    private Long userId;
}
