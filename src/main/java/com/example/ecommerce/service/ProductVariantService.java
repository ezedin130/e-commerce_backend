package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto.ProductOutDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantInDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantOutDto;
import com.example.ecommerce.mapper.ProductVariantMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository variantRepo;
    private final ProductVariantMapper variantMapper;
    private final ProductRepository productRepo;

    public ProductVariantOutDto createVariant(ProductVariantInDto dto){
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("product not found"));
        ProductVariant variant = variantMapper.toEntity(dto,product);
        ProductVariant savedVariant = variantRepo.save(variant);
        return variantMapper.toDto(savedVariant);
    }
    public List<ProductVariantOutDto> getAllVariants(){
        return variantRepo.findAll().stream()
                .map(variantMapper::toDto)
                .collect(Collectors.toList());
    }
}
