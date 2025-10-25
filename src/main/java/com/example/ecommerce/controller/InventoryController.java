package com.example.ecommerce.controller;

import com.example.ecommerce.dto.InventoryDto.InventoryInDto;
import com.example.ecommerce.dto.InventoryDto.InventoryOutDto;
import com.example.ecommerce.mapper.InventoryMapper;
import com.example.ecommerce.model.Inventory;
import com.example.ecommerce.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class InventoryController {
    @Autowired
    private final InventoryService service;
    @Autowired
    private final InventoryMapper mapper;

    @PostMapping("/inventory")
    public ResponseEntity<InventoryOutDto> createInventory(@RequestBody InventoryInDto dto){
        InventoryOutDto result = service.createInventory(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-inventory-by-id/{id}")
    public ResponseEntity<InventoryOutDto> getInventoryById(@PathVariable Long id){
        Inventory inventory = service.getInventoryById(id);
        InventoryOutDto dto = mapper.toDto(inventory);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryOutDto>> getAllInventory(){
        List<InventoryOutDto> result = service.getAllInventories();
        return ResponseEntity.ok(result);
    }
}
