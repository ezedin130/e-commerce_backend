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
    //ToDo : there should be some way to find store by owner
    //ToDo :  make sure there is way to change store fields using put or patch operation specifically after authentication and authorization are enabled
}
