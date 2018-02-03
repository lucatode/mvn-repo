package com.abc.project.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Output {
    private final List<Item> items;
    private final BigDecimal total;
    private final BigDecimal tax;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Output output = (Output) o;
        return Objects.equals(items, output.items) &&
                Objects.equals(total, output.total) &&
                Objects.equals(tax, output.tax);
    }

    @Override
    public int hashCode() {

        return Objects.hash(items, total, tax);
    }
}
