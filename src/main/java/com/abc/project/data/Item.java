package com.abc.project.data;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private final BigDecimal price;
    private final int quantity;
    private final boolean imported;
    private final String name;
    private final ItemType type;

    public Item(BigDecimal price, boolean imported, String name, ItemType type, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.price.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.price.precision();
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
    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return imported == item.imported &&
                Objects.equals(price, item.price) &&
                Objects.equals(name, item.name) &&
                type == item.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(price, imported, name, type);
    }


}
