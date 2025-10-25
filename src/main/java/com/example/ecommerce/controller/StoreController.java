package com.example.ecommerce.controller;

import com.example.ecommerce.dto.StoreDto.StoreInDto;
import com.example.ecommerce.dto.StoreDto.StoreOutDto;
import com.example.ecommerce.mapper.StoreMapper;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class StoreController {
    @Autowired
    private final StoreService service;
    @Autowired
    private final StoreMapper mapper;

    @PostMapping("/store")
    public ResponseEntity<StoreOutDto> createRole(@RequestBody StoreInDto dto){
        StoreOutDto result = service.createStore(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-store-by-id/{id}")
    public ResponseEntity<StoreOutDto> getStoreById(@PathVariable Long id){
        Store store = service.findStoreById(id);
        StoreOutDto dto = mapper.toDto(store);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/store")
    public ResponseEntity<List<StoreOutDto>> getAllStores(){
        List<StoreOutDto> result = service.getAllStores();
        return ResponseEntity.ok(result);
    }
}
