package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDto.ProductInDto;
import com.example.ecommerce.dto.ProductDto.ProductOutDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Store;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductInDto dto, Store store){
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .store(store)
                .build();
    }
    public ProductOutDto toDto(Product product){
        ProductOutDto dto = new ProductOutDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStoreId(product.getStore().getId());
        return dto;
    }
}
