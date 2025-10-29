package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDto.OrderInDto;
import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepo;
    @Autowired
    private final OrderMapper mapper;
    @Autowired
    private final OrderItemService itemService;
    @Autowired
    private final ProductVariantRepository variantRepo;
    public OrderOutDto createOrder(OrderInDto dto){
        ProductVariant variant = variantRepo.findById(dto.getProductVariantId())
                .orElseThrow(()-> new RuntimeException("Product Variant Not Found"));
        Order order = mapper.toEntity(dto,variant);
        Order savedOrder = orderRepo.save(order);
        itemService.createOrderItemForOrder(variant.getId(),savedOrder);
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
