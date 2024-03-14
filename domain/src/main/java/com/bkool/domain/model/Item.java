package com.bkool.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

    private Long id;
    private String model;
    private String type;
    private String description;

    protected Item(){}


    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    protected void setModel(String model) {
        this.model = model;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(model, item.model) && Objects.equals(type, item.type) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, type, description);
    }
}
