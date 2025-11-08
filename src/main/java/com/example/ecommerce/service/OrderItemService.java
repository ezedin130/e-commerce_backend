package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderItemDto.OrderItemInDto;
import com.example.ecommerce.dto.OrderItemDto.OrderItemOutDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantInDto;
import com.example.ecommerce.mapper.OrderItemMapper;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductVariantRepository;
import com.example.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    @Autowired
    private final OrderItemRepository itemRepo;
    @Autowired
    private final OrderRepository orderRepo;
    @Autowired
    private final ProductVariantRepository variantRepository;
    @Autowired
    private final OrderItemMapper mapper;
    @Autowired
    private final UserRepository userRepo;
    public OrderItemOutDto createOrderItem(OrderItemInDto dto){
        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order Not Found"));
        OrderItem orderItem = mapper.toEntity(dto,order);
        OrderItem savedItem = itemRepo.save(orderItem);
        return mapper.toDto(savedItem);
    }
    @Transactional
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
    public OrderItemOutDto updateOrderItem(Long itemId, int newQuantity, String username){
        User currentUser = userRepo.findByUsername(username);
        if(currentUser == null){
            throw new RuntimeException("User not found");
        }
        OrderItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Order-item Not Found"));
        User owner = item.getOrder().getUser();
        if(!owner.getId().equals(currentUser.getId())){
            throw new RuntimeException("Unauthorized: You can only update order items for your own order");
        }
        item.setQuantity(newQuantity);
        item.setSubTotalPrice(item.getUnitPrice() * newQuantity);
        OrderItem savedItem = itemRepo.save(item);
        return mapper.toDto(savedItem);
    }
}
