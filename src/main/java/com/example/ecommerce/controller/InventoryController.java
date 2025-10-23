package com.example.ecommerce.controller;

import com.example.ecommerce.dto.InventoryDto.InventoryInDto;
import com.example.ecommerce.dto.InventoryDto.InventoryOutDto;
import com.example.ecommerce.service.InventoryService;
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
public class InventoryController {
    private final InventoryService service;
    @PostMapping("/inventory")
    public ResponseEntity<InventoryOutDto> createInventory(@RequestBody InventoryInDto dto){
        InventoryOutDto result = service.createInventory(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryOutDto>> getAllInventory(){
        List<InventoryOutDto> result = service.getAllInventories();
        return ResponseEntity.ok(result);
    }
}
