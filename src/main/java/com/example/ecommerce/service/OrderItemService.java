package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDto.OrderOutDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemInDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemOutDto;
import com.example.ecommerce.mapper.OrderItemMapper;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository itemRepo;
    private final OrderRepository orderRepo;
    private final ProductVariantRepository variantRepository;
    private final OrderItemMapper mapper;
    public OrderItemOutDto createOrderItem(OrderItemInDto dto){
        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order Not Found"));
        ProductVariant variant = variantRepository.findById(dto.getProductVariantId())
                .orElseThrow(()-> new RuntimeException("Product Variant Not Found"));
        OrderItem orderItem = mapper.toEntity(dto,order,variant);
        OrderItem savedItem = itemRepo.save(orderItem);
        return mapper.toDto(savedItem);
    }
    public OrderItem getOrderItemById(Long id){
        return itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
    }
    public List<OrderItemOutDto> getAllItems(){
        return itemRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
