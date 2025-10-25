package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDto.OrderInDto;
import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService service;
    @Autowired
    private final OrderMapper mapper;

    @PostMapping("/order")
    public ResponseEntity<OrderOutDto> createOrder(@RequestBody OrderInDto dto){
        OrderOutDto result = service.createOrder(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-order-by-id/{id}")
    public ResponseEntity<OrderOutDto> getOrderById(@PathVariable Long id){
        Order order =  service.getOrderById(id);
        OrderOutDto dto = mapper.toDto(order);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/order")
    public ResponseEntity<List<OrderOutDto>> getAllOrders(){
        List<OrderOutDto> result = service.getAllOrders();
        return ResponseEntity.ok(result);
    }
}
