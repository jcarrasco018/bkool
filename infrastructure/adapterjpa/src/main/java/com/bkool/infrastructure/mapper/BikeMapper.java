package com.bkool.infrastructure.mapper;



import com.bkool.domain.model.Bike;
import com.bkool.domain.model.BikeBuilder;
import com.bkool.infrastructure.entities.BikeEntity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class BikeMapper {

    private BikeMapper(){}

    public static Bike toDto(BikeEntity bikeEntity) {
        if (Objects.nonNull(bikeEntity)) {
            return BikeBuilder.builder()
                    .id(bikeEntity.getId())
                    .name(bikeEntity.getName())
                    .description(bikeEntity.getDescription())
                    .price(bikeEntity.getPrice())
                    .manufacturer(bikeEntity.getManufacturer())
                    .items(Optional.ofNullable(bikeEntity.getItems()).orElse(new ArrayList<>()).stream().map(ItemMapper::toDto).toList())
                    .build();
        }
        return null;
    }

    public static BikeEntity toEntity(Bike bike){
        if (Objects.nonNull(bike)) {
            BikeEntity bikeEntity = new BikeEntity();
            bikeEntity.setId(bike.getId());
            bikeEntity.setName(bike.getName());
            bikeEntity.setDescription(bike.getDescription());
            bikeEntity.setPrice(bike.getPrice());
            bikeEntity.setManufacturer(bike.getManufacturer());
            bikeEntity.setItems(Optional.ofNullable(bike.getItems()).orElse(new ArrayList<>()).stream().map(ItemMapper::toEntity).toList());
            return bikeEntity;
        }
        return null;
    }

}
