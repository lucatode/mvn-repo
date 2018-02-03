package com.abc.project.model;

import java.math.BigDecimal;
import java.util.List;

public class Output {
    private final BigDecimal total;
    private final BigDecimal tax;
    private final List<Item> items;

    public Output(
            BigDecimal total,
            BigDecimal tax,
            List<Item> items){
        this.total = total;
        this.tax = tax;
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public List<Item> getItems() {
        return items;
    }
}
