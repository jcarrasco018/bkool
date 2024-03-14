package com.bkool.infraestructure.mapper;


import com.bkool.domain.model.Item;
import com.bkool.domain.model.ItemBuilder;
import com.bkool.infraestructure.dto.ItemDTO;

import java.util.Objects;

public class ItemDTOMapper {

    private ItemDTOMapper() {
    }

    public static ItemDTO toDTO(Item item) {
        if (Objects.nonNull(item)) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setModel(item.getModel());
            itemDTO.setType(item.getType());
            itemDTO.setDescription(item.getDescription());
            return itemDTO;
        }
        return null;
    }


    public static Item toEntity(ItemDTO itemDTO) {
        if (Objects.nonNull(itemDTO)) {
            return ItemBuilder.builder()
                    .id(itemDTO.getId())
                    .model(itemDTO.getModel())
                    .type(itemDTO.getType())
                    .description(itemDTO.getDescription()).build();
        }
        return null;
    }
}
