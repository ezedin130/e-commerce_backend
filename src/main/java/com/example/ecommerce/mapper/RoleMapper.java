package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.RoleDto.RoleInDto;
import com.example.ecommerce.dto.RoleDto.RoleOutDto;
import com.example.ecommerce.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public Role toEntity(RoleInDto dto){
        return Role.builder()
                .name(dto.getName())
                .build();
    }
    public RoleOutDto toDto(Role role){
        RoleOutDto dto = new RoleOutDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
