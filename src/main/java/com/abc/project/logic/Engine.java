package com.abc.project.logic;

import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Engine {



    // - tax calc
    // - calc tax for this item => return tax
    // - sum to totalTax
    // - build updated item
    // - add item to outputList
    // - total calc
    // - sum updated items
    // - build output

    //Evaluate rule parser for more complex problems: https://stackoverflow.com/questions/20763189/creating-a-simple-rule-engine-in-java


    public Output calculate(List<Item> items){
        List<ItemType> taxExceptions = new ArrayList<ItemType>();
        taxExceptions.add(ItemType.BOOK);
        taxExceptions.add(ItemType.FOOD);
        taxExceptions.add(ItemType.MEDICAL);

        BigDecimal totalTax = new BigDecimal("0.0");
        BigDecimal total = new BigDecimal("0.0");
        List<Item> outputItems = new ArrayList<Item>();

        for (final Item item: items) {
            //calculate tax
            BigDecimal tax = getTaxToApply(item, taxExceptions);
            //update tax total
            totalTax = totalTax.add(tax);

            //updating price
            BigDecimal newPrice = item.getPrice().add(tax);
            //update sum total
            total = total.add(newPrice);

            //build output items
            Item outputItem = new Item(newPrice, item.isImported(), item.getName(), item.getType());
            outputItems.add(outputItem);
        }
        //build output
        return new Output(total, totalTax, outputItems);

    }

    public BigDecimal getTaxToApply(Item item, List<ItemType> exceptions){
        final BigDecimal baseTax = new BigDecimal("0.10");
        final BigDecimal importedTax = new BigDecimal("0.05");
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
        BigDecimal tax = roundNearestFiveCent(taxValue);

        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
        return tax;
    }

    public BigDecimal roundNearestFiveCent(BigDecimal value ){
        double doubleResult = Math.ceil( value.doubleValue() * 20.00)/20.00;
        BigDecimal result = BigDecimal.valueOf(doubleResult)
                .setScale(2,  BigDecimal.ROUND_HALF_UP);
        return result;
    }


}
