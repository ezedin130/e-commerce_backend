package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDto.OrderInDto;
import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.service.OrderService;
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
public class OrderController {
    private final OrderService service;
    @PostMapping("/order")
    public ResponseEntity<OrderOutDto> createOrder(@RequestBody OrderInDto dto){
        OrderOutDto result = service.createOrder(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/order")
    public ResponseEntity<List<OrderOutDto>> getAllOrders(){
        List<OrderOutDto> result = service.getAllOrders();
        return ResponseEntity.ok(result);
    }
}
