package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderItemDto.OrderItemInDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemOutDto;
import com.example.ecommerce.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService service;
    @PostMapping("/order-item")
    public ResponseEntity<OrderItemOutDto> createOrderItem(@RequestBody OrderItemInDto dto){
        OrderItemOutDto result = service.createOrderItem(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/order-item")
    public ResponseEntity<List<OrderItemOutDto>> getAllOrderItems(){
        List<OrderItemOutDto> result = service.getAllItems();
        return ResponseEntity.ok(result);
    }
}
