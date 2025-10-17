package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductVariantDto.ProductVariantInDto;
import com.example.ecommerce.dto.ProductVariantDto.ProductVariantOutDto;
import com.example.ecommerce.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService  variantService;

    @PostMapping("/variant")
    public ResponseEntity<ProductVariantOutDto> createVariant(@RequestBody ProductVariantInDto dto){
        ProductVariantOutDto result = variantService.createVariant(dto);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/variant")
    public ResponseEntity<List<ProductVariantOutDto>> getAllVariants(){
        List<ProductVariantOutDto> result = variantService.getAllVariants();
        return ResponseEntity.ok().body(result);
    }

}
