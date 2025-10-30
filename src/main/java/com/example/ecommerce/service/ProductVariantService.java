package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductVariantDto.ProductVariantInDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantOutDto;
import com.example.ecommerce.mapper.ProductVariantMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    @Autowired
    private final ProductVariantRepository variantRepo;
    @Autowired
    private final ProductVariantMapper variantMapper;
    @Autowired
    private final ProductRepository productRepo;
    @Autowired
    private final InventoryService inventoryService;

    public ProductVariantOutDto createVariant(ProductVariantInDto dto){
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("product not found"));
        ProductVariant variant = variantMapper.toEntity(dto,product);
        ProductVariant savedVariant = variantRepo.save(variant);
        Store store = product.getStore();
        inventoryService.createInventoryForStore(store.getId(), savedVariant.getId(), 0);
        return variantMapper.toDto(savedVariant);
    }
    public ProductVariantOutDto createVariantForProduct(Product product){
         String defaultSize = "Standard";
        String defaultColor = "Default";
        ProductVariant variant = ProductVariant.builder()
                .size(defaultSize)
                .color(defaultColor)
                .product(product)
                .build();
        ProductVariant savedVariant = variantRepo.save(variant);
        Store store = product.getStore();
        if (store != null) {
            inventoryService.createInventoryForStore(store.getId(), savedVariant.getId(), 0);
        } else {
            throw new RuntimeException("Cannot create inventory — product has no store assigned");
        }

        return variantMapper.toDto(savedVariant);
    }
    public ProductVariant getVariantById(Long id){
        return variantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("variant not found"));
    }
    public List<ProductVariantOutDto> getAllVariants(){
        return variantRepo.findAll().stream()
                .map(variantMapper::toDto)
                .collect(Collectors.toList());
    }
}
