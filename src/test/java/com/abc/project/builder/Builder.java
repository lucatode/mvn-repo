package com.abc.project.builder;

import com.abc.project.logic.calculator.TaxCalculator;
import com.abc.project.logic.rounder.DecimalRounder;
import com.abc.project.logic.rounder.UpFiveCentRounder;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Builder {
    public static ItemBuilder item() {
        return new ItemBuilder();
    }
    public static class ItemBuilder {

        private BigDecimal price = new BigDecimal("10.00");
        private ItemType type = ItemType.STANDARD;
        private boolean imported = false;
        private String name = "Item";

        public ItemBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder setPrice(final BigDecimal price) {
            this.price = price;
            return this;
        }
        public ItemBuilder setPrice(final String price) {
            this.price = new BigDecimal(price);
            return this;
        }
        public ItemBuilder setPrice(final double price) {
            this.price = new BigDecimal(price);
            return this;
        }

        public ItemBuilder setImported(final boolean imported) {
            this.imported = imported;
            return this;
        }

        public ItemBuilder setType(ItemType type) {
            this.type = type;
            return this;
        }

        public Item build() {
            return new Item(price, imported, name, type);
        }


    }

    public static TaxCalculatorBuilder taxCalculator() {
        return new TaxCalculatorBuilder();
    }
    public static class TaxCalculatorBuilder {

        private DecimalRounder rounder;
        private List<ItemType> exceptions;
        private BigDecimal baseTax;
        private BigDecimal importedTax;

        public TaxCalculatorBuilder(){
            this.rounder = new UpFiveCentRounder();
            this.exceptions = new ArrayList<ItemType>();
            this.baseTax = new BigDecimal("0.10");
            this.importedTax = new BigDecimal("0.05");
        }

        public TaxCalculatorBuilder addTypeToException(ItemType type) {
            this.exceptions.add(type);
            return this;
        }


        public TaxCalculator build() {
            return new TaxCalculator(this.rounder, this.exceptions, this.baseTax, this.importedTax);
        }


    }

    public static OutputBuilder output() {
        return new OutputBuilder();
    }
    public static class OutputBuilder {
        private List<Item> items;
        private BigDecimal total;
        private BigDecimal taxes;

        public OutputBuilder(){
            this.items = new ArrayList<Item>();
            this.total = new BigDecimal("0.00");
            this.taxes = new BigDecimal("0.00");
        }

        public OutputBuilder addItem(Item item) {
            this.items.add(item);
            return this;
        }

        public OutputBuilder setTotal(BigDecimal total){
            this.total = total;
            return this;
        }

        public OutputBuilder setTaxes(BigDecimal taxes){
            this.taxes = taxes;
            return this;
        }

        public Output build() {
            return new Output(this.total, this.taxes, this.items);
        }


    }

}
