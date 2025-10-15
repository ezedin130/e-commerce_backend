package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.StoreDto.StoreInDto;
import com.example.ecommerce.dto.StoreDto.StoreOutDto;
import com.example.ecommerce.model.Store;
import org.springframework.stereotype.Service;

@Service
public class StoreMapper {
    public Store toEntity(StoreInDto dto){
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
    }
    public StoreOutDto toDto(Store store){
        StoreOutDto dto = new StoreOutDto();
        dto.setId(store.getId());
        dto.setName(store.getName());
        return dto;
    }
}
