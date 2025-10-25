package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductVariantDto.ProductVariantInDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantOutDto;
import com.example.ecommerce.mapper.ProductVariantMapper;
import com.example.ecommerce.model.ProductVariant;
import com.example.ecommerce.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class ProductVariantController {
    @Autowired
    private final ProductVariantService  variantService;
    @Autowired
    private final ProductVariantMapper mapper;

    @PostMapping("/variant")
    public ResponseEntity<ProductVariantOutDto> createVariant(@RequestBody ProductVariantInDto dto){
        ProductVariantOutDto result = variantService.createVariant(dto);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/get-variant-by-id/{id}")
    public ResponseEntity<ProductVariantOutDto> getVariantById(@PathVariable Long id){
        ProductVariant variant = variantService.getVariantById(id);
        ProductVariantOutDto dto = mapper.toDto(variant);
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("/variant")
    public ResponseEntity<List<ProductVariantOutDto>> getAllVariants(){
        List<ProductVariantOutDto> result = variantService.getAllVariants();
        return ResponseEntity.ok().body(result);
    }

}
