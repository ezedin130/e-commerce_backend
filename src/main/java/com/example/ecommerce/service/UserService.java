package com.example.ecommerce.service;

import com.example.ecommerce.dto.AuthResponseDto;
import com.example.ecommerce.dto.UserDto.UserInDto;
import com.example.ecommerce.dto.UserDto.UserLoginDto;
import com.example.ecommerce.dto.UserDto.UserOutDto;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserMapper mapper;
    @Autowired
    private final UserRepository userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private final AuthenticationManager authManager;
    @Autowired
    private final JwtService jwtService;

    public UserOutDto createUser(UserInDto dto){
        User user = mapper.toEntity(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return mapper.toDto(savedUser);
    }
    public AuthResponseDto login(UserLoginDto dto){
        Authentication auth = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  dto.getUserName(),
                  dto.getPassword())
        );
        if(auth.isAuthenticated()){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(dto.getUserName());

            User user = userRepo.findByUsername(dto.getUserName());
            Set<String> roleNames = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());
            return new AuthResponseDto(
                    token,
                    user.getUsername(),
                    roleNames
            );
        }
        return null;
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
    public void changeOwnPassword(String username, String currentPassword, String newPassword) {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new RuntimeException("User not found");
        }

        if (!encoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
    }
    public void resetUserPassword(Long targetUserId, String newPassword){
        User user = userRepo.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
    }
}
