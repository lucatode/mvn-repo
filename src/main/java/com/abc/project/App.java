package com.abc.project;

import com.abc.project.io.GsonInputManager;
import com.abc.project.io.InputManager;
import com.abc.project.io.GsonOutputManager;
import com.abc.project.io.OutputManager;
import com.abc.project.logic.Engine;
import com.abc.project.logic.calculator.TaxCalculator;
import com.abc.project.logic.calculator.TaxPercentageCalculator;
import com.abc.project.logic.rounder.UpFiveCentRounder;
import com.abc.project.data.Item;
import com.abc.project.data.ItemType;
import com.abc.project.data.Output;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args){

        //Setup
        final Gson gson = new Gson();
        final InputManager<Item> inputManager = new GsonInputManager(gson);
        final Engine engine = new Engine(LoadConfig());
        final OutputManager<Output> outputManager = new GsonOutputManager(gson);

        // - Input
        List<Item> items = inputManager.getList(input_example_1());
        // - Calculate
        Output output = engine.calculate(items);
        // - Output
        String outputString = outputManager.write(output);
        // - Print
        System.out.println(outputString);

    }

    private static TaxCalculator LoadConfig(){
        final List<ItemType> taxException = new ArrayList<>();
        taxException.add(ItemType.FOOD);
        taxException.add(ItemType.BOOK);
        taxException.add(ItemType.MEDICAL);

        final TaxPercentageCalculator tpc = new TaxPercentageCalculator(
                taxException,
                new BigDecimal("0.10"),
                new BigDecimal("0.05")
        );

        final TaxCalculator tc = new TaxCalculator(new UpFiveCentRounder(), tpc);

        return tc;
    }

    private static String input_example_1(){
        final String inputExample01 = "[{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"book\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 12.49,\n" +
                "  \"type\": \"BOOK\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"music CD\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 14.99,\n" +
                "  \"type\": \"STANDARD\"\n" +
                "},{\n" +
                "  \"quantity\": 1,\n" +
                "  \"name\": \"chocolate bar\",\n" +
                "  \"imported\": false,\n" +
                "  \"price\": 0.85,\n" +
                "  \"type\": \"FOOD\"\n" +
                "}]";
        return inputExample01;
    }

}
