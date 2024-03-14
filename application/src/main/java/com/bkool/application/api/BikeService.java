package com.bkool.application.api;



import com.bkool.domain.model.Bike;

import java.util.List;

public interface BikeService {

    Bike save(Bike bike);

    List<Bike> search(String name, String manufacturer, String itemType, String order);
}
