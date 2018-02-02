package com.abc.project.model;

import java.math.BigDecimal;

public class Item {

    private final BigDecimal price;
    private final boolean imported;
    private final String name;
    private final ItemType type;

    public Item(BigDecimal price, boolean imported, String name, ItemType type) {
        this.price = price;
        this.imported = imported;
        this.name = name;
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public boolean isImported() {
        return imported;
    }
    public String getName() {
        return name;
    }
    public ItemType getType() {
        return type;
    }
}
