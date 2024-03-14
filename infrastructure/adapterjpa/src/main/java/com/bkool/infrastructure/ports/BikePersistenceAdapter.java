package com.bkool.infrastructure.ports;

import com.bkool.domain.model.Bike;
import com.bkool.domain.model.BikeBuilder;
import com.bkool.domain.model.Item;
import com.bkool.domain.model.ItemBuilder;
import com.bkool.domain.persistence.BikePersistencePort;
import com.bkool.infrastructure.entities.BikeEntity;
import com.bkool.infrastructure.mapper.BikeMapper;
import com.bkool.infrastructure.repositories.BikeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;

import java.util.*;

public class BikePersistenceAdapter implements BikePersistencePort {

    private final BikeRepository bikeRepository;

    public BikePersistenceAdapter(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Bike save(Bike bike) {
        if (Objects.nonNull(bike)) {
            return BikeMapper.toDto(bikeRepository.save(Objects.requireNonNull(BikeMapper.toEntity(bike))));
        }
        return null;
    }

    @Override
    @Cacheable("searchBikes")
    public List<Bike> search(String name, String manufacturer, String itemType, String order) {
        if(Objects.nonNull(name) || Objects.nonNull(manufacturer) || Objects.nonNull(order) || Objects.nonNull(itemType)){
            Item item = Objects.nonNull(itemType) ? ItemBuilder.builder().type(itemType).build():null;
            BikeEntity filter = BikeMapper.toEntity(BikeBuilder.builder().name(name).manufacturer(manufacturer).items(Collections.singletonList(item)).build());
            if (Objects.nonNull(filter)) {
                Sort.Direction direction = Sort.Direction.valueOf(Optional.ofNullable(order).orElse("asc").toUpperCase());
                List<BikeEntity> bikes = bikeRepository.findByItemsIn(name,manufacturer,itemType,Sort.by(direction, "name"));
                return bikes.stream().map(BikeMapper::toDto).toList();
            }
        }
        return new ArrayList<>();
    }
}
