package com.example.ecommerce.controller;

import com.example.ecommerce.dto.StoreDto.StoreInDto;
import com.example.ecommerce.dto.StoreDto.StoreOutDto;
import com.example.ecommerce.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class StoreController {
    private final StoreService service;

    @PostMapping("/store")
    public ResponseEntity<StoreOutDto> createRole(@RequestBody StoreInDto dto){
        StoreOutDto result = service.createStore(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/store")
    public ResponseEntity<List<StoreOutDto>> getAllStores(){
        List<StoreOutDto> result = service.getAllStores();
        return ResponseEntity.ok(result);
    }
}
