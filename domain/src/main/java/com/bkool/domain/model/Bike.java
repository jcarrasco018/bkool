package com.bkool.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Bike implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String manufacturer;
    private List<Item> items;

    protected Bike(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<Item> getItems() {
        return items;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setPrice(Double price) {
        this.price = price;
    }

    protected void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    protected void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(id, bike.id) && Objects.equals(name, bike.name) && Objects.equals(description, bike.description) && Objects.equals(price, bike.price) && Objects.equals(manufacturer, bike.manufacturer) && Objects.equals(items, bike.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, manufacturer, items);
    }
}
