package com.abc.project.model;

import java.math.BigDecimal;

public class Item {

    private final BigDecimal price;
    private final boolean imported;
    private final String name;

    public Item(BigDecimal price, boolean imported, String name) {
        this.price = price;
        this.imported = imported;
        this.name = name;
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
}
