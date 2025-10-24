package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDto.ProductInDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantInDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantOutDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductVariant;
import org.springframework.stereotype.Service;

@Service
public class ProductVariantMapper {
    public ProductVariant toEntity(ProductVariantInDto dto, Product product){
        return ProductVariant.builder()
                .size(dto.getSize())
                .color(dto.getColor())
                .product(product)
                .build();
    }
    public ProductVariantOutDto toDto(ProductVariant productVariant){
        //ToDo: make sure the @transient fields are applicable when needed
        ProductVariantOutDto dto = new ProductVariantOutDto();
        dto.setId(productVariant.getId());
        dto.setSize(productVariant.getSize());
        dto.setColor(productVariant.getColor());
        dto.setAdditionalPrice(productVariant.getAdditionalPrice());
        dto.setDiscount(productVariant.getDiscount());
        dto.setProductId(productVariant.getProduct().getId());
        return dto;
    }
}
