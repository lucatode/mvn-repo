package com.abc.project.logic;

import com.abc.project.builder.Builder;
import com.abc.project.data.Item;
import com.abc.project.data.ItemType;
import com.abc.project.logic.calculator.PercentageCalculator;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class PercentageCalculatorTest {


    @Test // Setup: { No exception } => 10% expected
    public void calculate_item_percentageExpected(){
        //Setup
        PercentageCalculator pc = Builder.taxPercCalculator().build();
        Item i = Builder.item().build();

        //Execute
        BigDecimal perc = pc.calculate(i);

        //Verify
        BigDecimal expectedPerc = new BigDecimal("0.10");
        assertEquals(expectedPerc, perc);
    }

    @Test // Setup: { FOOD }, Item: { FOOD } => 0% expected
    public void calculate_item_percentageExpected01(){
        //Setup
        PercentageCalculator pc = Builder.taxPercCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .build();
        Item i = Builder.item().setType(ItemType.FOOD).build();

        //Execute
        BigDecimal perc = pc.calculate(i);

        //Verify
        BigDecimal expectedPerc = new BigDecimal("0.00");
        assertEquals(expectedPerc, perc);
    }

    @Test // Setup: { FOOD }, Item: { FOOD, Imported } => 5% expected
    public void calculate_item_percentageExpected02(){
        //Setup
        PercentageCalculator pc = Builder.taxPercCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item i = Builder.item()
                .setType(ItemType.FOOD)
                .setImported(true)
                .build();

        //Execute
        BigDecimal perc = pc.calculate(i);

        //Verify
        BigDecimal expectedPerc = new BigDecimal("0.05");
        assertEquals(expectedPerc, perc);
    }

    @Test // Setup: { FOOD }, Item: { STANDARD, Imported } => 15% expected
    public void calculate_item_percentageExpected03(){
        //Setup
        PercentageCalculator pc = Builder.taxPercCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .build();

        Item i = Builder.item()
                .setType(ItemType.STANDARD)
                .setImported(true)
                .build();

        //Execute
        BigDecimal perc = pc.calculate(i);

        //Verify
        BigDecimal expectedPerc = new BigDecimal("0.15");
        assertEquals(expectedPerc, perc);
    }

}
