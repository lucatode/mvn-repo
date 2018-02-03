package com.abc.project.logic;

import com.abc.project.builder.Builder;
import com.abc.project.logic.calculator.TaxCalculator;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TaxCalculatorTest {

    @Test //Input: {item} - ExpectedOutput: { notNull }
    public void calculate_ItemAndExceptionList_ResultNotNull(){
        //Setup
        TaxCalculator tc = Builder.taxCalculator().build();
        Item item = Builder.item().build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertNotNull(b);
    }

    @Test //Input: { item:{ 10.00, true, STANDARD}:{BOOK} } - ExpectedOutput: { 1.50 }
    public void calculate_ItemAndExceptionList_OneAndAHalf(){
        //Setup
        BigDecimal expectedTax = new BigDecimal("1.50");
        TaxCalculator tc = Builder.taxCalculator().addTypeToExceptions(ItemType.BOOK).build();
        Item item = Builder.item()
                .setPrice("10.00")
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertEquals(expectedTax, b);
    }

    @Test //Input: { item:{ 10.00, true, STANDARD}:{BOOK,FOOD} } - ExpectedOutput: { 1.50 }
    public void calculate_ItemAndTwoElementExceptionsList_OneAndAHalf() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("1.50");
        TaxCalculator tc = Builder.taxCalculator()
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item item = Builder.item()
                .setPrice("10.00")
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertEquals(expectedTax, b);
    }

    @Test //Input: { item:{ 10.00, false, FOOD}:{BOOK,FOOD} } - ExpectedOutput: { 0.00 }
    public void calculate_ItemAndTwoElementExceptionsList_One() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("0.00");
        TaxCalculator tc = Builder.taxCalculator()
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item item = Builder.item()
                .setPrice("10.00")
                .setImported(false)
                .setType(ItemType.FOOD)
                .build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertEquals(expectedTax, b);
    }

    @Test //Input: { item:{ 10.00, true, FOOD}:{BOOK,FOOD} } - ExpectedOutput: { 0.50 }
    public void calculate_ItemAndTwoElementExceptionsList_ExpectedOneAndAHalf() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("0.50");
        TaxCalculator tc = Builder.taxCalculator()
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item item = Builder.item()
                .setPrice("10.00")
                .setImported(true)
                .setType(ItemType.FOOD)
                .build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertEquals(expectedTax, b);
    }

    //Goal: 4.1985 rounded to 4.20
    @Test //Input: { item:{ 27.99, true, STANDARD}:{BOOK,FOOD} } - ExpectedOutput: { 4.20 }
    public void calculate_ItemAndTwoElementExceptionsList_ExpectedOutput() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("4.20");
        TaxCalculator tc = Builder.taxCalculator()
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item item = Builder.item()
                .setPrice("27.99")
                .setImported(true)
                .setType(ItemType.STANDARD)
                .build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertEquals(expectedTax, b);
    }

    //Goal: 0.5625 rounded to 0.60
    @Test //Input: { item:{ 11.25, true, FOOD}:{BOOK,FOOD} } - ExpectedOutput: { 0.60 }
    public void calculate_Item_ExpectedRoundedOutput() {
        //Setup
        BigDecimal expectedTax = new BigDecimal("0.60");
        TaxCalculator tc = Builder.taxCalculator()
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item item = Builder.item()
                .setPrice("11.25")
                .setImported(true)
                .setType(ItemType.FOOD)
                .build();

        //Execute
        BigDecimal b = tc.calculate(item);

        //Verify
        assertEquals(expectedTax, b);
    }


}
