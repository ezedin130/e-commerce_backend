package com.example.ecommerce.dto.InventoryDto;

import lombok.Data;

@Data
public class InventoryInDto {
    private Integer quantity;
    private Long storeId;
    private Long productVariantId;
}
