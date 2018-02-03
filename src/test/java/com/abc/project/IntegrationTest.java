package com.abc.project;
import com.abc.project.builder.Builder;
import com.abc.project.io.ItemsInputManager;
import com.abc.project.logic.Engine;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void integrationTest_Example_1(){

        //Calculate output
        ItemsInputManager iim = new ItemsInputManager();
        Engine e = new Engine(Builder.taxCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.MEDICAL)
                .build());
        List<Item> items = iim.getList(new Gson(), Builder.input_example_1()); //example_2
        Output o = e.calculate(items);

        //Assert output is equals to expect output
        Output expectedOutput = Builder.output_example_1();
        assertEquals(expectedOutput.hashCode(), o.hashCode());
    }

    @Test
    public void integrationTest_Example_2(){
        //Calculate output
        ItemsInputManager iim = new ItemsInputManager();
        Engine e = new Engine(Builder.taxCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.MEDICAL)
                .build());
        List<Item> items = iim.getList(new Gson(), Builder.input_example_2()); //example_2
        Output o = e.calculate(items);

        //Assert output is equals to expect output
        Output expectedOutput = Builder.output_example_2();
        assertEquals(expectedOutput.hashCode(), o.hashCode());
    }

    @Test
    public void integrationTest_Example_3(){
        //Calculate output
        ItemsInputManager iim = new ItemsInputManager();
        Engine e = new Engine(Builder.taxCalculator()
                .addTypeToExceptions(ItemType.FOOD)
                .addTypeToExceptions(ItemType.BOOK)
                .addTypeToExceptions(ItemType.MEDICAL)
                .build());
        List<Item> items = iim.getList(new Gson(), Builder.input_example_3()); //example_2
        Output o = e.calculate(items);

        //Assert output is equals to expect output
        Output expectedOutput = Builder.output_example_3();
        assertEquals(expectedOutput.hashCode(), o.hashCode());
    }
}
