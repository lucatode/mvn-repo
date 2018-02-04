package com.abc.project;
import com.abc.project.builder.Builder;
import com.abc.project.io.GsonInputManager;
import com.abc.project.io.InputManager;
import com.abc.project.logic.Engine;
import com.abc.project.data.Item;
import com.abc.project.data.ItemType;
import com.abc.project.data.Output;
import com.abc.project.logic.calculator.TaxPercentageCalculator;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void integrationTest_Example_1(){

        //Calculate output
        InputManager<Item> iim = new GsonInputManager(new Gson());
        TaxPercentageCalculator tpc = Builder.taxPercCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.MEDICAL)
                .build();
        Engine e = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = iim.getList(Builder.input_example_1()); //example_1
        Output o = e.calculate(items);

        //Assert output is equals to expect output
        Output expectedOutput = Builder.output_example_1();
        assertEquals(expectedOutput.hashCode(), o.hashCode());
    }

    @Test
    public void integrationTest_Example_2(){
        //Calculate output
        InputManager<Item> iim = new GsonInputManager(new Gson());
        TaxPercentageCalculator tpc = Builder.taxPercCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.MEDICAL)
                .build();
        Engine e = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = iim.getList(Builder.input_example_2()); //example_2
        Output o = e.calculate(items);

        //Assert output is equals to expect output
        Output expectedOutput = Builder.output_example_2();
        assertEquals(expectedOutput.hashCode(), o.hashCode());
    }

    @Test
    public void integrationTest_Example_3(){
        //Calculate output
        InputManager<Item> iim = new GsonInputManager(new Gson());
        TaxPercentageCalculator tpc = Builder.taxPercCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.MEDICAL)
                .build();
        Engine e = new Engine(Builder.taxCalculator().addPercetageCalculator(tpc).build());
        List<Item> items = iim.getList(Builder.input_example_3()); //example_3
        Output o = e.calculate(items);

        //Assert output is equals to expect output
        Output expectedOutput = Builder.output_example_3();
        assertEquals(expectedOutput.hashCode(), o.hashCode());
    }
}
