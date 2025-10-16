package com.example.ecommerce.service;

import com.example.ecommerce.dto.RoleDto.RoleInDto;
import com.example.ecommerce.dto.RoleDto.RoleOutDto;
import com.example.ecommerce.mapper.RoleMapper;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepo;
    private final RoleMapper roleMapper;

    public RoleOutDto createRole(RoleInDto dto){
        Role role = roleMapper.toEntity(dto);
        Role savedRole = roleRepo.save(role);
        return roleMapper.toDto(savedRole);
    }
    public List<RoleOutDto> getAllRoles(){
        return roleRepo.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }
}
