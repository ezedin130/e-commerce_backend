package com.example.ecommerce.dto.InventoryDto;

import lombok.Data;

@Data
public class InventoryOutDto {
    private Long id;
    private Integer quantity;
    private Long storeId;
    private Long productVariantId;
}
