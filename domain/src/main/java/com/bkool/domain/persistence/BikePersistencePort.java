package com.bkool.domain.persistence;

import com.bkool.domain.model.Bike;

import java.util.List;

public interface BikePersistencePort {

    Bike save(Bike bike);

    List<Bike> search(String name, String manufacturer, String itemType,String order);

}
