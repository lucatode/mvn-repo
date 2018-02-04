package com.abc.project.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Output {
    private final List<Item> items;
    private final BigDecimal total;
    private final BigDecimal taxes;


    public Output(
            BigDecimal total,
            BigDecimal taxes,
            List<Item> items){
        this.total = total;
        this.taxes = taxes;
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Output output = (Output) o;
        return Objects.equals(items, output.items) &&
                Objects.equals(total, output.total) &&
                Objects.equals(taxes, output.taxes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(items, total, taxes);
    }
}
