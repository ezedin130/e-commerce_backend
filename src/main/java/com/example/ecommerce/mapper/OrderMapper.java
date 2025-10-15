package com.example.ecommerce.mapper;

import com.example.ecommerce.constant.OrderStatus;
import com.example.ecommerce.dto.OrderDto.OrderInDto;
import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderMapper {
    public Order toEntity(OrderInDto dto){
        return Order.builder()
                .orderStatus(OrderStatus.valueOf(dto.getOrderStatus()))
                .orderDate(LocalDate.now())
                .totalPrice(dto.getTotalPrice())
                .build();
    }
    public OrderOutDto toDto(Order order){
        OrderOutDto dto = new OrderOutDto();
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus().name());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }
}
