package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.InventoryDto.InventoryInDto;
import com.example.ecommerce.dto.InventoryDto.InventoryOutDto;
import com.example.ecommerce.model.Inventory;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.model.Store;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {
    public Inventory toEntity(InventoryInDto dto, Store store, ProductVariant productVariant) {
        return Inventory.builder()
                .quantity(dto.getQuantity())
                .store(store)
                .productVariant(productVariant)
                .build();
    }
    public InventoryOutDto toDto(Inventory inventory){
        InventoryOutDto dto = new InventoryOutDto();
        dto.setId(inventory.getId());
        dto.setQuantity(inventory.getQuantity());
        dto.setStoreId(inventory.getStore().getId());
        dto.setProductVariantId(inventory.getProductVariant().getId());
        return dto;
    }
}
