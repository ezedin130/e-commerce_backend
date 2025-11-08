package com.example.ecommerce.dto.UserDto;

import lombok.Data;

import java.util.Set;

@Data
public class UserInDto {
    private String firstName;
    private String lastName;
    private Set<Long> roleIds;
}
