package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.UserDto.UserInDto;
import com.example.ecommerce.dto.UserDto.UserOutDto;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toEntity(UserInDto dto){
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .build();
    }
    public UserOutDto toDto(User user){
        UserOutDto dto = new UserOutDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserName(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
