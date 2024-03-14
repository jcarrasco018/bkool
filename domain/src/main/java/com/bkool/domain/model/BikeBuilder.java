package com.bkool.domain.model;

import java.util.List;

public class BikeBuilder {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String manufacturer;

    private List<Item> items;

    private BikeBuilder(){}

    public static BikeBuilder builder(){
        return new BikeBuilder();
    }

    public Bike build(){
        Bike bike = new Bike();
        bike.setId(id);
        bike.setName(name);
        bike.setDescription(description);
        bike.setPrice(price);
        bike.setManufacturer(manufacturer);
        bike.setItems(items);
        return bike;
    }

    public BikeBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public BikeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BikeBuilder description(String description) {
        this.description = description;
        return this;
    }

    public BikeBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public BikeBuilder manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public BikeBuilder items(List<Item> items) {
        this.items = items;
        return this;
    }
}
