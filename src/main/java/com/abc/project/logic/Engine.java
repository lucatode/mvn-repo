package com.abc.project.logic;

import com.abc.project.logic.calculator.Calculator;
import com.abc.project.data.Item;
import com.abc.project.data.Output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Engine {

    private final Calculator<Item,BigDecimal> taxCalculator;

    public Engine(Calculator<Item,BigDecimal> taxCalculator) {
        this.taxCalculator = taxCalculator;
    }


    /* RESPONSABILITIES */
    // - ENGINE => GET INPUT RETURN OUTPUT
        // - DELEGATE TAX CALC
            // - SHOULD BE INITIALIZED FROM CTOR
        // - ITEM PRICE UPDATE
        // - DELEGATE OUTPUT BUILDING


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
        BigDecimal totalTax = new BigDecimal("0.0");
        BigDecimal total = new BigDecimal("0.0");
        List<Item> outputItems = new ArrayList<Item>();

        for (final Item item: items) {
            //calculate tax
            BigDecimal tax = taxCalculator.calculate(item);
            //update tax total
            totalTax = totalTax.add(tax);

            //updating price
            BigDecimal newPrice = item.getPrice().add(tax);
            //update sum total
            total = total.add(newPrice);

            //build output items
            Item outputItem = new Item(newPrice, item.isImported(), item.getName(), item.getType(), item.getQuantity());
            outputItems.add(outputItem);
        }

        //build output
        return new Output(total, totalTax, outputItems);

    }

}
