package com.abc.project.logic;

import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class EngineTest {

    public static ItemBuilder builder() {
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

    /*************
        CALCULATE
     **************/

    //Output
    @Test
    public void calculate_Items_OutputNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output != null);
    }

    //Total
    @Test
    public void calculate_Items_OutputTotalNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTotal() != null);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected total: 16.49
    public void calculate_OneItem_TotalEqualsItemPrice(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder()
                .setPrice(new BigDecimal("14.99"))
                .setImported(false)
                .setType(ItemType.STANDARD)
                .build());
        BigDecimal expectedPrice = new BigDecimal("16.49");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTotal().equals(expectedPrice));
    }

    @Test
    public void calculate_TwoItemsList_TotalAsExpected(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder().setPrice(new BigDecimal("10.00")).setImported(true).setType(ItemType.FOOD).build());
        items.add( builder().setPrice(new BigDecimal("47.50")).setImported(true).setType(ItemType.STANDARD).build());
        BigDecimal expectedPrice = new BigDecimal("65.15");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTotal().equals(expectedPrice));
    }


    //Items
    @Test
    public void calculate_Items_OutputItemListNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getItems() != null);
    }

    @Test
    public void calculate_OneItemList_OneElementInOutputItemList(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add(builder().build());

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getItems().size() == 1);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected item output price: 16.49
    public void calculate_OneItemList_OutputItemPriceUpdated(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder()
                .setPrice(new BigDecimal("14.99"))
                .setImported(false)
                .setType(ItemType.STANDARD)
                .build());
        BigDecimal expectedPrice = new BigDecimal("16.49");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        Item firstItem = output.getItems().get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    @Test //Input: { 12.49, false, BOOK } - Expected item output price: 12.49
    public void calculate_OneItemList_OutputItemPriceAsOriginalPrice(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder()
                .setPrice(new BigDecimal("12.49"))
                .setImported(false)
                .setType(ItemType.FOOD)
                .build());
        BigDecimal expectedPrice = new BigDecimal("12.49");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        Item firstItem = output.getItems().get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    @Test //Input: { 47.50, true, STANDARD } - Expected item output price: 54.65
    public void calculate_OneImportedItemList_OutputItemPriceUpdated(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder()
                .setPrice(new BigDecimal("47.50"))
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build());
        BigDecimal expectedPrice = new BigDecimal("54.65");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        Item firstItem = output.getItems().get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    @Test //Input: { 10.00, true, FOOD } - Expected item output price: 10.50
    public void calculate_OneFOODImportedItemList_OutputItemPriceUpdated(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder()
                .setPrice(new BigDecimal("10.00"))
                .setImported(true)
                .setType(ItemType.FOOD)
                .build());
        BigDecimal expectedPrice = new BigDecimal("10.50");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        Item firstItem = output.getItems().get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    //Tax
    @Test
    public void calculate_Items_OutputTaxNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTax() != null);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected tax: 1.50
    public void calculate_OneItemList_TaxAsExpected(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder().setPrice(new BigDecimal("14.99")).setImported(false).setType(ItemType.STANDARD).build());
        BigDecimal expectedTax = new BigDecimal("1.50");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTax().equals(expectedTax));
    }

    @Test //Input: [{ 10.00, true, FOOD },{ 47.50, true, STANDARD }] - Expected tax: 7.65
    public void calculate_TwoItemsList_TaxAsExpected(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder().setPrice(new BigDecimal("10.00")).setImported(true).setType(ItemType.FOOD).build());
        items.add( builder().setPrice(new BigDecimal("47.50")).setImported(true).setType(ItemType.STANDARD).build());
        BigDecimal expectedTax = new BigDecimal("7.65");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTax().equals(expectedTax));
    }



    /************************
     GET TAX TO APPLY
     *************************/
    @Test //Input: {item, exceptionList} - ExpectedOutput: { notNull }
    public void getTaxToApply_ItemAndExceptionList_ResultNotNull(){
        //Setup
        Engine e = new Engine();
        Item item = builder().build();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertNotNull(b);
    }

    @Test //Input: { item:{ 10.00, true, STANDARD}, exceptionList:{BOOK} } - ExpectedOutput: { 1.50 }
    public void getTaxToApply_ItemAndExceptionList_OneAndAHalf(){
        //Setup
        BigDecimal expectedTax = new BigDecimal("1.50");
        Engine e = new Engine();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);
        Item item = builder()
                .setPrice("10.00")
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build();

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertEquals(expectedTax, b);
    }

    @Test //Input: { item:{ 10.00, true, STANDARD}, exceptionList:{BOOK,FOOD} } - ExpectedOutput: { 1.50 }
    public void getTaxToApply_ItemAndTwoElementExceptionsList_OneAndAHalf() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("1.50");
        Engine e = new Engine();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);
        exceptionList.add(ItemType.FOOD);
        Item item = builder()
                .setPrice("10.00")
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build();

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertEquals(expectedTax, b);
    }

    @Test //Input: { item:{ 10.00, false, FOOD}, exceptionList:{BOOK,FOOD} } - ExpectedOutput: { 0.00 }
    public void getTaxToApply_ItemAndTwoElementExceptionsList_One() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("0.00");
        Engine e = new Engine();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);
        exceptionList.add(ItemType.FOOD);
        Item item = builder()
                .setPrice("10.00")
                .setImported(false)
                .setType(ItemType.FOOD)
                .build();

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertEquals(expectedTax, b);
    }

    @Test //Input: { item:{ 10.00, true, FOOD}, exceptionList:{BOOK,FOOD} } - ExpectedOutput: { 0.50 }
    public void getTaxToApply_ItemAndTwoElementExceptionsList_ExpectedOneAndAHalf() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("0.50");
        Engine e = new Engine();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);
        exceptionList.add(ItemType.FOOD);
        Item item = builder()
                .setPrice("10.00")
                .setImported(true)
                .setType(ItemType.FOOD)
                .build();

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertEquals(expectedTax, b);
    }

    //Goal: 4.1985 rounded to 4.20
    @Test //Input: { item:{ 27.99, true, STANDARD}, exceptionList:{BOOK,FOOD} } - ExpectedOutput: { 4.20 }
    public void getTaxToApply_ItemAndTwoElementExceptionsList_ExpectedOutput() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("4.20");
        Engine e = new Engine();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);
        exceptionList.add(ItemType.FOOD);
        Item item = builder()
                .setPrice("27.99")
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build();

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertEquals(expectedTax, b);
    }

    //Goal: 0.5625 rounded to 0.60
    @Test //Input: { item:{ 11.25, true, FOOD}, exceptionList:{BOOK,FOOD} } - ExpectedOutput: { 0.60 }
    public void getTaxToApply_Item_ExpectedRoundedOutput() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("0.60");
        Engine e = new Engine();
        List<ItemType> exceptionList = new ArrayList<ItemType>();
        exceptionList.add(ItemType.BOOK);
        exceptionList.add(ItemType.FOOD);
        Item item = builder()
                .setPrice("11.25")
                .setImported(true)
                .setType(ItemType.FOOD)
                .build();

        //Execute
        BigDecimal b = e.getTaxToApply(item, exceptionList);

        //Verify
        assertEquals(expectedTax, b);
    }



    /************************
     ROUND UP TO THE NEAREST FIVE CENT
     *************************/

}
