package com.example.ecommerce.dto.PaymentDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentOutDto {
    private Long id;
    private double amount;
    private LocalDateTime paymentDate;
    private Long orderId;
}
