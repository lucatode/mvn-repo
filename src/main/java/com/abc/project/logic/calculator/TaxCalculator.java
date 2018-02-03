package com.abc.project.logic.calculator;

import com.abc.project.logic.rounder.DecimalRounder;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;

import java.math.BigDecimal;
import java.util.List;

public class TaxCalculator implements Calculator<Item, BigDecimal> {

    final DecimalRounder rounder;
    final List<ItemType> exceptions;
    final BigDecimal baseTax = new BigDecimal("0.10");
    final BigDecimal importedTax = new BigDecimal("0.05");

    public TaxCalculator(
            DecimalRounder rounder,
            List<ItemType> exceptions,
            BigDecimal baseTax,
            BigDecimal importedTax){

        this.rounder = rounder;
        this.exceptions = exceptions;
    }

    public BigDecimal calculate(Item item) {

        BigDecimal taxPerc = new BigDecimal("0.00");

        if(!exceptions.contains(item.getType())){
            taxPerc = taxPerc.add(baseTax);
        }

        if(item.isImported()){
            taxPerc = taxPerc.add(importedTax);
        }

        BigDecimal itemPrice = item.getPrice();
        itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal taxValue = (itemPrice.multiply(taxPerc));
        BigDecimal tax = rounder.round(taxValue);

        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
        return tax;
    }
}
