package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDto.OrderInDto;
import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderMapper mapper;
    public OrderOutDto createOrder(OrderInDto dto){
        Order order = mapper.toEntity(dto);
        Order savedOrder = orderRepo.save(order);
        return mapper.toDto(savedOrder);
    }
    public  Order getOrderById(Long id){
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found"));
    }
    public List<OrderOutDto> getAllOrders(){
        return orderRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
