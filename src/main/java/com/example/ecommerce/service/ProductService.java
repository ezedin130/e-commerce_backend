package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto.ProductInDto;
import com.example.ecommerce.dto.ProductDto.ProductOutDto;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository prodRepo;
    private StoreRepository storeRepo;
    private final ProductMapper mapper;

    public ProductOutDto createProduct(ProductInDto dto){
        Store store = storeRepo.findById(dto.getStoreId())
                .orElseThrow(()-> new RuntimeException("Store not found"));
        Product product = mapper.toEntity(dto,store);
        Product savedProduct = prodRepo.save(product);
        return mapper.toDto(savedProduct);
    }
    public List<ProductOutDto> findAllProducts(){
        return prodRepo.findAll().stream()
                .map(mapper :: toDto)
                .collect(Collectors.toList());
    }
}
