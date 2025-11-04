package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto.UserInDto;
import com.example.ecommerce.dto.UserDto.UserOutDto;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserMapper mapper;
    @Autowired
    private final UserRepository userRepo;

    public UserOutDto createUser(UserInDto dto){
        User user = mapper.toEntity(dto);
        User savedUser = userRepo.save(user);
        return mapper.toDto(savedUser);
    }
    public User findUserById(Long id){
       return userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Store with " +id+" id is not found"));
    }
    public List<UserOutDto> findAllUsers(){
        return userRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
