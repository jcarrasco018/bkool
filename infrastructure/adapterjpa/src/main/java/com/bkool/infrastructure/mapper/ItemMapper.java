package com.bkool.infrastructure.mapper;




import com.bkool.domain.model.Item;
import com.bkool.domain.model.ItemBuilder;
import com.bkool.infrastructure.entities.ItemEntity;

import java.util.Objects;

public class ItemMapper {

    private ItemMapper(){}

    public static Item toDto(ItemEntity itemEntity){
        if (Objects.nonNull(itemEntity)) {
           return ItemBuilder.builder()
                   .id(itemEntity.getId())
                   .model(itemEntity.getModel())
                   .type(itemEntity.getType())
                   .description(itemEntity.getDescription())
                   .build();
        }
        return null;
    }

    public static ItemEntity toEntity(Item item){
        if (Objects.nonNull(item)) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setId(item.getId());
            itemEntity.setModel(item.getModel());
            itemEntity.setType(item.getType());
            itemEntity.setDescription(item.getDescription());
        return itemEntity;
        }
        return null;
    }

}
