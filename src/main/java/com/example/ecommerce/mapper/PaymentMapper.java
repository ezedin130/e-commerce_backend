package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.PaymentDto.PaymentInDto;
import com.example.ecommerce.dto.PaymentDto.PaymentOutDto;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentMapper {
    public Payment toEntity(PaymentInDto dto, Order order){
        return Payment.builder()
                .amount(dto.getAmount())
                .paymentDate(LocalDateTime.now())
                .order(order)
                .build();
    }
    public PaymentOutDto toDto(Payment payment){
        PaymentOutDto dto = new PaymentOutDto();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setOrderId(payment.getOrder().getId());
        return dto;
    }
}
