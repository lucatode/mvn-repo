package com.abc.project.logic;

import com.abc.project.builder.Builder;
import com.abc.project.data.Item;
import com.abc.project.data.ItemType;
import com.abc.project.data.Output;
import com.abc.project.logic.calculator.TaxPercentageCalculator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class EngineTest {

    /*************
        CALCULATE
     **************/

    //Output
    @Test
    public void calculate_Items_OutputNotNull(){
        //Setup
        Engine engine = new Engine(Builder.taxCalculator().build());
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
        Engine engine = new Engine(Builder.taxCalculator().build());
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTotal() != null);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected total: 16.49
    public void calculate_OneItem_TotalEqualsItemPrice(){
        //Setup
        Engine engine = new Engine(Builder.taxCalculator().build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item()
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
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item().setPrice(new BigDecimal("10.00")).setImported(true).setType(ItemType.FOOD).build());
        items.add( Builder.item().setPrice(new BigDecimal("47.50")).setImported(true).setType(ItemType.STANDARD).build());
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
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getItems() != null);
    }

    @Test
    public void calculate_OneItemList_OneElementInOutputItemList(){
        //Setup
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add(Builder.item().build());

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getItems().size() == 1);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected item output price: 16.49
    public void calculate_OneItemList_OutputItemPriceUpdated(){
        //Setup
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item()
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
    public void calculate_OneItemList_OutputItemPriceAsOriginalPrice()/**/{
        //Setup
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item()
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
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item()
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
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item()
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
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTaxes() != null);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected tax: 1.50
    public void calculate_OneItemList_TaxAsExpected(){
        //Setup
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item().setPrice(new BigDecimal("14.99")).setImported(false).setType(ItemType.STANDARD).build());
        BigDecimal expectedTax = new BigDecimal("1.50");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTaxes().equals(expectedTax));
    }

    @Test //Input: [{ 10.00, true, FOOD },{ 47.50, true, STANDARD }] - Expected tax: 7.65
    public void calculate_TwoItemsList_TaxAsExpected(){
        //Setup
        TaxPercentageCalculator tpc = Builder.taxPercCalculator().addTypeToExceptions(ItemType.FOOD).build();
        Engine engine = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = new ArrayList<Item>();
        items.add( Builder.item().setPrice(new BigDecimal("10.00")).setImported(true).setType(ItemType.FOOD).build());
        items.add( Builder.item().setPrice(new BigDecimal("47.50")).setImported(true).setType(ItemType.STANDARD).build());
        BigDecimal expectedTax = new BigDecimal("7.65");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTaxes().equals(expectedTax));
    }


}
