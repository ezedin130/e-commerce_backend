package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RoleDto.RoleInDto;
import com.example.ecommerce.dto.RoleDto.RoleOutDto;
import com.example.ecommerce.mapper.RoleMapper;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    private final RoleService service;
    @Autowired
    private final RoleMapper mapper;

    @PostMapping("/role")
    public ResponseEntity<RoleOutDto> createRole(@RequestBody RoleInDto dto){
        RoleOutDto result = service.createRole(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-role-by-id/{id}")
    public ResponseEntity<RoleOutDto> getRoleById(@PathVariable Long id){
        Role role = service.findRoleById(id);
        RoleOutDto dto = mapper.toDto(role);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/role")
    public ResponseEntity<List<RoleOutDto>> getAllRoles(){
        List<RoleOutDto> result = service.getAllRoles();
        return ResponseEntity.ok(result);
    }
}
