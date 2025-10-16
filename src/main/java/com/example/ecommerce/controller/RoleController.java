package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RoleDto.RoleInDto;
import com.example.ecommerce.dto.RoleDto.RoleOutDto;
import com.example.ecommerce.service.RoleService;
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
public class RoleController {
    private final RoleService service;

    @PostMapping("/role")
    public ResponseEntity<RoleOutDto> createRole(@RequestBody RoleInDto dto){
        RoleOutDto result = service.createRole(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/role")
    public ResponseEntity<List<RoleOutDto>> getAllRoles(){
        List<RoleOutDto> result = service.getAllRoles();
        return ResponseEntity.ok(result);
    }
}
