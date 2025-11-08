package com.example.ecommerce.dto.UserDto;

import lombok.Data;

@Data
public class UserPasswordChangeDto {
    private String currentPassword;
    private String newPassword;
}
