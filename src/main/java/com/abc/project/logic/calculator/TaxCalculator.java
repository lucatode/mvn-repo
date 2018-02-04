package com.abc.project.logic.calculator;

import com.abc.project.logic.rounder.DecimalRounder;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;

import java.math.BigDecimal;
import java.util.List;

public class TaxCalculator implements Calculator<Item, BigDecimal> {

    private final DecimalRounder rounder;
    private final List<ItemType> exceptions;
    private final BigDecimal baseTax;
    private final BigDecimal importedTax;

    public TaxCalculator(
            DecimalRounder rounder,
            List<ItemType> exceptions,
            BigDecimal baseTax,
            BigDecimal importedTax){

        this.rounder = rounder;
        this.exceptions = exceptions;
        this.baseTax = baseTax;
        this.importedTax = importedTax;
    }

    public BigDecimal calculate(Item item) {

        BigDecimal taxPerc = new BigDecimal("0.00");

        if(!this.exceptions.contains(item.getType())){
            taxPerc = taxPerc.add(this.baseTax);
        }

        if(item.isImported()){
            taxPerc = taxPerc.add(this.importedTax);
        }

        BigDecimal itemPrice = item.getPrice();
        itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal taxValue = (itemPrice.multiply(taxPerc)).multiply(new BigDecimal(item.getQuantity()));
        BigDecimal tax = this.rounder.round(taxValue);

        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);

        return tax;
    }
}
