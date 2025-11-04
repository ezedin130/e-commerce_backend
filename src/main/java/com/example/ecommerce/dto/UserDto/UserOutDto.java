package com.example.ecommerce.dto.UserDto;

import lombok.Data;

import java.util.Set;

@Data
public class UserOutDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Set<Long> roleIds;
}
