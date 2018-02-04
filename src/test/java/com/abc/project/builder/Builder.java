package com.abc.project.builder;

import com.abc.project.logic.calculator.TaxCalculator;
import com.abc.project.logic.rounder.DecimalRounder;
import com.abc.project.logic.rounder.UpFiveCentRounder;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;
import com.google.gson.Gson;

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
        private int quantity = 1;

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
            this.price = this.price.setScale(2, BigDecimal.ROUND_HALF_UP);
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

        public ItemBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Item build() {
            return new Item(price, imported, name, type, quantity);
        }

        public String toJsonArray(){
            Item[] items = new Item[1];
            items[0] = new Item(price, imported, name, type, quantity);
            String json = new Gson().toJson(items, Item[].class);
            return json;
        }

        public String toJson(){
            String json = new Gson().toJson(new Item(price, imported, name, type, quantity), Item.class);
            return json;
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

        public TaxCalculatorBuilder addTypeToExceptions(ItemType type) {
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

        public OutputBuilder setTotal(String total){
            this.total = new BigDecimal(total);
            return this;
        }


        public OutputBuilder setTaxes(BigDecimal taxes){
            this.taxes = taxes;
            return this;
        }

        public OutputBuilder setTaxes(String taxes){
            this.taxes = new BigDecimal(taxes);
            return this;
        }

        public Output build() {
            return new Output(this.total, this.taxes, this.items);
        }


    }


    public static String input_example_1(){
        final String inputExample01 = "[{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"book\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 12.49,\n" +
                "  \"type\": \"BOOK\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"music CD\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 14.99,\n" +
                "  \"type\": \"STANDARD\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"chocolate bar\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 0.85,\n" +
                "  \"type\": \"FOOD\"\n" +
                "}]";
        return inputExample01;
    }
    public static String input_example_2(){
        return "[{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"box of chocolates\",\n" +
                "  \"imported\": true,\n" +
                "  \"price\": 10.00,\n" +
                "  \"type\": \"FOOD\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"bottle of perfume\",\n" +
                "  \"imported\": true,\n" +
                "  \"price\": 47.50,\n" +
                "  \"type\": \"STANDARD\"\n" +
                "}]";
    }
    public static String input_example_3(){
        return "[{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"bottle of perfume\",\n" +
                "  \"imported\": true,\n" +
                "  \"price\": 27.99,\n" +
                "  \"type\": \"STANDARD\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"bottle of perfume\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 18.99,\n" +
                "  \"type\": \"STANDARD\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"packet of headache pills\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 9.75,\n" +
                "  \"type\": \"MEDICAL\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"box of chocolates\",\n" +
                "  \"imported\": true,\n" +
                "  \"price\": 11.25,\n" +
                "  \"type\": \"FOOD\"\n" +
                "}]";
    }

    public static Output output_example_1(){

        Item item00 = Builder.item().setImported(false).setName("book").setType(ItemType.BOOK).setPrice("12.49").build();
        Item item01 = Builder.item().setImported(false).setName("music CD").setType(ItemType.STANDARD).setPrice("16.49").build();
        Item item02 = Builder.item().setImported(false).setName("chocolate bar").setType(ItemType.FOOD).setPrice("0.85").build();
        return Builder.output()
                .setTotal("29.83")
                .setTaxes("1.50")
                .addItem(item00)
                .addItem(item01)
                .addItem(item02)
                .build();
    }
    public static Output output_example_2() {
        Item item00 = Builder.item().setImported(true).setName("box of chocolates").setType(ItemType.FOOD).setPrice("10.50").build();
        Item item01 = Builder.item().setImported(true).setName("bottle of perfume").setType(ItemType.STANDARD).setPrice("54.65").build();
        return Builder.output()
                .setTotal("65.15")
                .setTaxes("7.65")
                .addItem(item00)
                .addItem(item01)
                .build();
    }
    public static Output output_example_3(){
        Item item00 = Builder.item().setImported(true).setName("bottle of perfume").setType(ItemType.STANDARD).setPrice("32.19").build();
        Item item01 = Builder.item().setImported(false).setName("bottle of perfume").setType(ItemType.STANDARD).setPrice("20.89").build();
        Item item02 = Builder.item().setImported(false).setName("packet of headache pills").setType(ItemType.MEDICAL).setPrice("9.75").build();
        Item item03 = Builder.item().setImported(true).setName("box of chocolates").setType(ItemType.FOOD).setPrice("11.85").build();

        return Builder.output()
                .setTotal("74.68")
                .setTaxes("6.70")
                .addItem(item00)
                .addItem(item01)
                .addItem(item02)
                .addItem(item03)
                .build();
    }

}
