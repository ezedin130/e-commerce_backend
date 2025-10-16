package com.example.ecommerce.service;

import com.example.ecommerce.dto.StoreDto.StoreInDto;
import com.example.ecommerce.dto.StoreDto.StoreOutDto;
import com.example.ecommerce.mapper.StoreMapper;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepo;
    private final StoreMapper storeMapper;

    public StoreOutDto createStore(StoreInDto dto){
        Store store = storeMapper.toEntity(dto);
        Store savedStore = storeRepo.save(store);
        return storeMapper.toDto(savedStore);
    }
    public List<StoreOutDto> getAllStores(){
        return storeRepo.findAll().stream()
                .map(storeMapper::toDto)
                .collect(Collectors.toList());
    }
}
