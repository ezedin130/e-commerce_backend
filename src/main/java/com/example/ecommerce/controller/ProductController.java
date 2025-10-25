package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto.ProductInDto;
import com.example.ecommerce.dto.ProductDto.ProductOutDto;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService prodService;
    @Autowired
    private final ProductMapper mapper;

    @PostMapping("/product")
    public ResponseEntity<ProductOutDto> createProduct(@RequestBody ProductInDto dto){
        ProductOutDto result = prodService.createProduct(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<ProductOutDto> getProductById(@PathVariable Long id){
        Product prod = prodService.getProductById(id);
        ProductOutDto dto = mapper.toDto(prod);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductOutDto>> getAllProducts(){
        List<ProductOutDto> result = prodService.findAllProducts();
        return ResponseEntity.ok(result);
    }
}
