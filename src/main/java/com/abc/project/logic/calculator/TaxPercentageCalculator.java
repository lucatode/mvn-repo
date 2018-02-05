package com.abc.project.logic.calculator;

import com.abc.project.data.Item;
import com.abc.project.data.ItemType;

import java.math.BigDecimal;
import java.util.List;

public class TaxPercentageCalculator implements Calculator<Item,BigDecimal> {
    private final List<ItemType> exceptions;
    private final BigDecimal baseTax;
    private final BigDecimal importedTax;

    public TaxPercentageCalculator(List<ItemType> exceptions, BigDecimal baseTax, BigDecimal importedTax) {
        this.exceptions = exceptions;
        this.baseTax = baseTax;
        this.importedTax = importedTax;
    }

    @Override
    public BigDecimal calculate(Item item) {
        BigDecimal taxPerc = new BigDecimal("0.00");

        if(!this.exceptions.contains(item.getType())){
            taxPerc = taxPerc.add(this.baseTax);
        }

        if(item.isImported()){
            taxPerc = taxPerc.add(this.importedTax);
        }

        return taxPerc;
    }
}
