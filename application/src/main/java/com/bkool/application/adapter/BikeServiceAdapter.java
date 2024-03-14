package com.bkool.application.adapter;

import com.bkool.application.api.BikeService;
import com.bkool.domain.model.Bike;
import com.bkool.domain.persistence.BikePersistencePort;

import java.util.List;
import java.util.Objects;

public class BikeServiceAdapter implements BikeService {
    private final BikePersistencePort bikePersistencePort;
    public BikeServiceAdapter(BikePersistencePort bikePersistencePort) {
        this.bikePersistencePort = bikePersistencePort;
    }

    @Override
    public Bike save(Bike bike){
        if(Objects.nonNull(bike)){
            return bikePersistencePort.save(Objects.requireNonNull(bike));
        }
        return null;
    }

    @Override
    public List<Bike> search(String name, String manufacturer, String itemType, String order) {
        return bikePersistencePort.search(name, manufacturer, itemType, order);
    }

}
