package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.RoleDto.RoleOutDto;
import com.example.ecommerce.dto.UserDto.UserInDto;
import com.example.ecommerce.dto.UserDto.UserOutDto;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {
    @Autowired
    private final RoleRepository roleRepo;
    public User toEntity(UserInDto dto){
        Set<Role> roles = dto.getRoleIds().stream()
                .map(id -> roleRepo.findById(Long.valueOf(id))
                        .orElseThrow(() -> new RuntimeException("Role with " +id+ " id not found")))
                .collect(Collectors.toSet());
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .username(dto.getUserName())
                .roles(roles)
                .build();
    }
    public UserOutDto toDto(User user){
        Set<Long> roleIds = user.getRoles().stream()
                .map(Role::getId)  // Extract only the IDs
                .collect(Collectors.toSet());
        UserOutDto dto = new UserOutDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserName(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRoleIds(roleIds);
        return dto;
    }
    public RoleOutDto mapRoles(Role r){
        return new RoleOutDto(
                r.getId(),
                r.getName()
        );
    }
}
