package com.example.ecommerce.mapper;

import com.example.ecommerce.constant.OrderStatus;
import com.example.ecommerce.constant.PaymentStatus;
import com.example.ecommerce.dto.OrderDto.OrderInDto;
import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.ProductVariant;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderMapper {
    public Order toEntity(OrderInDto dto, ProductVariant variant){
        return Order.builder()
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.valueOf(dto.getOrderStatus()))
                .paymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()))
                .totalPrice(dto.getTotalPrice())
                .productVariant(variant)
                .build();
    }
    public OrderOutDto toDto(Order order){
        OrderOutDto dto = new OrderOutDto();
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus().name());
        dto.setOrderDate(order.getOrderDate());
        dto.setPaymentStatus(order.getPaymentStatus().name());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setProductVariantId(order.getProductVariant().getId());
        return dto;
    }
}
