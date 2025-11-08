package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemInDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemOutDto;
import com.example.ecommerce.mapper.OrderItemMapper;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class OrderItemController {
    @Autowired
    private final OrderItemService service;
    @Autowired
    private final OrderItemMapper mapper;

    @PostMapping("/order-item")
    public ResponseEntity<OrderItemOutDto> createOrderItem(@RequestBody OrderItemInDto dto){
        OrderItemOutDto result = service.createOrderItem(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-order-item-by-id/{id}")
    public ResponseEntity<OrderItemOutDto> getOrderItemById(@PathVariable Long id){
        OrderItem item = service.getOrderItemById(id);
        OrderItemOutDto dto = mapper.toDto(item);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/order-item")
    public ResponseEntity<List<OrderItemOutDto>> getAllOrderItems(){
        List<OrderItemOutDto> result = service.getAllItems();
        return ResponseEntity.ok(result);
    }
    @PutMapping("/order-item/{id}/quantity")
    public ResponseEntity<OrderItemOutDto> updateOrderItemQuantity(
            @PathVariable Long id,
            @RequestParam int quantity
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        OrderItemOutDto updated = service.updateOrderItem(id,quantity,username);
        return ResponseEntity.ok(updated);
    }
}
