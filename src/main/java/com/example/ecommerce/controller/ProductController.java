package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto.ProductInDto;
import com.example.ecommerce.dto.ProductDto.ProductOutDto;
import com.example.ecommerce.service.ProductService;
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
public class ProductController {
    private final ProductService prodService;

    @PostMapping("/product")
    public ResponseEntity<ProductOutDto> createProduct(@RequestBody ProductInDto dto){
        ProductOutDto result = prodService.createProduct(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductOutDto>> getAllProducts(){
        List<ProductOutDto> result = prodService.findAllProducts();
        return ResponseEntity.ok(result);
    }
}
