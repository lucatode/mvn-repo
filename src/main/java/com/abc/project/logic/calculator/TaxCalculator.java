package com.abc.project.logic.calculator;

import com.abc.project.logic.rounder.DecimalRounder;
import com.abc.project.data.Item;
import com.abc.project.data.ItemType;

import java.math.BigDecimal;
import java.util.List;

public class TaxCalculator implements Calculator<Item, BigDecimal> {

    private final DecimalRounder rounder;
    private Calculator<Item, BigDecimal> percentageCalculator;


    public TaxCalculator(DecimalRounder rounder, Calculator<Item,BigDecimal> percentageCalculator){

        this.rounder = rounder;
        this.percentageCalculator = percentageCalculator;
    }

    public BigDecimal calculate(Item item) {
        //Calculate percentage
        BigDecimal taxPerc = percentageCalculator.calculate(item);

        //GetValue
        BigDecimal itemPrice = item.getPrice();
        itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal taxValue = (itemPrice.multiply(taxPerc)).multiply(new BigDecimal(item.getQuantity()));

        //Round value up to the nearest five cents
        BigDecimal tax = this.rounder.round(taxValue);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);

        return tax;
    }

}
