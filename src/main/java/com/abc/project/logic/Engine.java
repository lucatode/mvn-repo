package com.abc.project.logic;

import com.abc.project.model.Item;
import com.abc.project.model.Output;

import java.math.BigDecimal;
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


        return new Output(new BigDecimal("10.00"), new BigDecimal("1.00"), items);

    }




}
