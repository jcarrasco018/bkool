package com.bkool.infraestructure.mapper;

import com.bkool.domain.model.Bike;
import com.bkool.domain.model.BikeBuilder;
import com.bkool.infraestructure.dto.BikeDTO;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class BikeDTOMapper {

    private BikeDTOMapper() {
    }

    public static BikeDTO toDTO(Bike bike) {
        if (Objects.nonNull(bike)) {
            BikeDTO bikeDTO = new BikeDTO();
            bikeDTO.setId(bike.getId());
            bikeDTO.setName(bike.getName());
            bikeDTO.setDescription(bike.getDescription());
            bikeDTO.setPrice(bike.getPrice());
            bikeDTO.setManufacturer(bike.getManufacturer());
            bikeDTO.setItems(Optional.ofNullable(bike.getItems()).orElse(new ArrayList<>()).stream().map(ItemDTOMapper::toDTO).toList());
            return bikeDTO;
        }
        return null;
    }

    public static Bike toEntity(BikeDTO bikeDTO) {
        if (Objects.nonNull(bikeDTO)) {
            return BikeBuilder.builder()
                    .id(bikeDTO.getId())
                    .name(bikeDTO.getName())
                    .description(bikeDTO.getDescription())
                    .price(bikeDTO.getPrice())
                    .manufacturer(bikeDTO.getManufacturer())
                    .items(Optional.ofNullable(bikeDTO.getItems()).orElse(new ArrayList<>()).stream().map(ItemDTOMapper::toEntity).toList())
                    .build();
        }
        return null;
    }
}
