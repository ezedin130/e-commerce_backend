package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AuthResponseDto;
import com.example.ecommerce.dto.UserDto.UserInDto;
import com.example.ecommerce.dto.UserDto.UserLoginDto;
import com.example.ecommerce.dto.UserDto.UserOutDto;
import com.example.ecommerce.dto.UserDto.UserPasswordChangeDto;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;

    @PostMapping("/create-user")
    public ResponseEntity<UserOutDto> createUser(@RequestBody UserInDto dto){
        UserOutDto result = userService.createUser(dto);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody UserLoginDto dto){
        return userService.login(dto);
    }
    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<UserOutDto> getUserById(@PathVariable Long id){
        User user = userService.findUserById(id);
        UserOutDto dto = userMapper.toDto(user);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserOutDto>> getAllUsers(){
        List<UserOutDto> result = userService.findAllUsers();
        return ResponseEntity.ok(result);
    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changeOwnPassword(@RequestBody UserPasswordChangeDto dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changeOwnPassword(username,dto.getCurrentPassword(),dto.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/reset-password")
    public ResponseEntity<String> resetUserPassword(
            @PathVariable Long id,
            @RequestBody UserPasswordChangeDto dto
    ) {
        userService.resetUserPassword(id, dto.getNewPassword());
        return ResponseEntity.ok("Password reset successfully");
    }
}
