package com.bkool.domain.cache;

import com.bkool.domain.model.Bike;

import java.util.List;

public interface BikeCachePersistencePort {
    List<Bike> search(String name, String manufacturer, List<String> itemType, String order);
}
