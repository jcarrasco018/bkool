package com.bkool.domain.model;

public class ItemBuilder {

    private Long id;
    private String model;
    private String type;
    private String description;

    private ItemBuilder(){}

    public static ItemBuilder builder(){
        return new ItemBuilder();
    }
    public Item build(){
        Item item = new Item();
        item.setId(id);
        item.setModel(model);
        item.setType(type);
        item.setDescription(description);
        return item;
    }

    public ItemBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ItemBuilder model(String model) {
        this.model = model;
        return this;
    }

    public ItemBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ItemBuilder description(String description) {
        this.description = description;
        return this;
    }
}
