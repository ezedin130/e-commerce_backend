package com.example.ecommerce.service;

import com.example.ecommerce.dto.StoreDto.StoreInDto;
import com.example.ecommerce.dto.StoreDto.StoreOutDto;
import com.example.ecommerce.mapper.StoreMapper;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.StoreRepository;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    @Autowired
    private final StoreRepository storeRepo;
    @Autowired
    private final StoreMapper storeMapper;
    @Autowired
    private final UserRepository userRepo;

    public StoreOutDto createStore(StoreInDto dto){
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(()-> new RuntimeException("user not found"));
        Store store = storeMapper.toEntity(dto,user);
        Store savedStore = storeRepo.save(store);
        return storeMapper.toDto(savedStore);
    }
    public Store findStoreById(Long id){
        return storeRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Store with" +id+ "id is not found"));
    }
    public List<StoreOutDto> getAllStores(){
        return storeRepo.findAll().stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }
    public StoreOutDto updateStore(Long storeId, StoreInDto dto, String username) {
        User currentUser = userRepo.findByUsername(username);
        if (currentUser == null) {
            throw new RuntimeException("User not found");
        }
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        if (!store.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized: You are not the owner of this store");
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            store.setName(dto.getName());
        }

        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            store.setAddress(dto.getAddress());
        }

        Store saved = storeRepo.save(store);
        return storeMapper.toDto(saved);
    }
}