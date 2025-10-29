package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderItemDto.OrderItemInDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemOutDto;
import com.example.ecommerce.mapper.OrderItemMapper;
import com.example.ecommerce.model.*;
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
        OrderItem orderItem = mapper.toEntity(dto,order);
        OrderItem savedItem = itemRepo.save(orderItem);
        return mapper.toDto(savedItem);
    }
    public OrderItemOutDto createOrderItemForOrder(Long variantId,Order order){
        int quantity = 1;
        ProductVariant variant = variantRepository.getReferenceById(variantId);
        Product product = variant.getProduct();
        double unitPrice = product.getPrice();
        double subTotalPrice = quantity * unitPrice;
        OrderItem item = OrderItem.builder()
                .quantity(quantity)
                .unitPrice(unitPrice)
                .subTotalPrice(subTotalPrice)
                .order(order)
                .build();
        OrderItem savedItem = itemRepo.save(item);
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
