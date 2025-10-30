package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.StoreDto.StoreInDto;
import com.example.ecommerce.dto.StoreDto.StoreOutDto;
import com.example.ecommerce.model.Store;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Service;

@Service
public class StoreMapper {
    public Store toEntity(StoreInDto dto, User user){
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .user(user)
                .build();
    }
    public StoreOutDto toDto(Store store){
        StoreOutDto dto = new StoreOutDto();
        dto.setId(store.getId());
        dto.setName(store.getName());
        dto.setAddress(store.getAddress());
        dto.setUserId(store.getUser().getId());
        return dto;
    }
}
