package com.abc.project.logic;

import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Engine {

    public Output calculate(List<Item> items){

        // - tax calc
        // - calc tax for this item => return tax
        // - sum to totalTax
        // - build updated item
        // - add item to outputList
        // - total calc
        // - sum updated items
        // - build output

        //Evaluate rule parser for more complex problems: https://stackoverflow.com/questions/20763189/creating-a-simple-rule-engine-in-java

        List<ItemType> taxExceptions = new ArrayList<ItemType>();
        taxExceptions.add(ItemType.BOOK);
        taxExceptions.add(ItemType.FOOD);
        taxExceptions.add(ItemType.MEDICAL);

        BigDecimal totalTax = new BigDecimal(0.0);
        BigDecimal total = new BigDecimal(0.0);
        List<Item> outputItems = new ArrayList<Item>();

        for (final Item item: items) {
            //TODO: refactor this.
            BigDecimal tax = getTaxToApply(item, taxExceptions);
            totalTax = totalTax.add(tax);
            BigDecimal newPrice = item.getPrice().add(tax);
            total = total.add(newPrice);
            Item outputItem = new Item(newPrice, item.isImported(), item.getName(), item.getType());
            outputItems.add(outputItem);
        }

        return new Output(total, totalTax, outputItems);

    }

    public BigDecimal getTaxToApply(Item item, List<ItemType> exceptions){
        double taxPerc = 0.00;

        if(!exceptions.contains(item.getType())){
            taxPerc += 0.10;
        }

        if(item.isImported()){
            taxPerc += 0.05;
        }

        BigDecimal itemPrice = item.getPrice();
        double taxValue = (itemPrice.doubleValue()* taxPerc);
        BigDecimal tax = new BigDecimal(roundNearestFiveCent(taxValue));

        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
        return tax;
    }

    public double roundNearestFiveCent(double value ){
        return Math.round(value * 20.00) / 20.00;
    }


}
