package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.OrderItemDto.OrderItemInDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemOutDto;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.ProductVariant;
import org.springframework.stereotype.Service;

@Service
public class OrderItemMapper {
    public OrderItem toEntity(OrderItemInDto dto, Order order, ProductVariant productVariant){
        return OrderItem.builder()
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .subTotalPrice(dto.getSubTotalPrice())
                .order(order)
                .productVariant(productVariant)
                .build();
    }
    public OrderItemOutDto toDto(OrderItem  orderItem){
        OrderItemOutDto dto = new OrderItemOutDto();
        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setSubTotalPrice(orderItem.getSubTotalPrice());
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setProductVariantId(orderItem.getProductVariant().getId());
        return dto;
    }
}
