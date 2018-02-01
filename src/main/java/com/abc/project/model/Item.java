package com.abc.project.model;

import java.math.BigDecimal;

public class Item {

    final BigDecimal price;
    final boolean imported;

    public Item(BigDecimal price, boolean imported) {
        this.price = price;
        this.imported = imported;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }
}
