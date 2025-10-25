package com.example.ecommerce.service;

import com.example.ecommerce.dto.InventoryDto.InventoryInDto;
import com.example.ecommerce.dto.InventoryDto.InventoryOutDto;
import com.example.ecommerce.mapper.InventoryMapper;
import com.example.ecommerce.model.Inventory;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.repository.InventoryRepository;
import com.example.ecommerce.repository.ProductVariantRepository;
import com.example.ecommerce.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepo;
    @Autowired
    private final ProductVariantRepository variantRepo;
    @Autowired
    private final StoreRepository storeRepo;
    @Autowired
    private final InventoryMapper mapper;
    public InventoryOutDto createInventory(InventoryInDto dto){
        ProductVariant variant = variantRepo.findById(dto.getProductVariantId())
                .orElseThrow(() -> new RuntimeException("product variant not found"));
        Store store = storeRepo.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("store not found"));
        Inventory inventory = mapper.toEntity(dto,store,variant);
        Inventory savedInventory = inventoryRepo.save(inventory);
        return mapper.toDto(savedInventory);
    }
    public Inventory getInventoryById(Long id){
        return inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("inventory not found"));
    }
    public List<InventoryOutDto> getAllInventories(){
        return inventoryRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
