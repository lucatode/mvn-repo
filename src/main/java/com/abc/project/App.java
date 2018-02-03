package com.abc.project;

import com.abc.project.io.ItemsInputManager;
import com.abc.project.io.JsonWriter;
import com.abc.project.logic.Engine;
import com.abc.project.logic.calculator.TaxCalculator;
import com.abc.project.logic.rounder.UpFiveCentRounder;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args){

        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        final Engine engine = new Engine(CreateTaxCalculator());
        final JsonWriter jsonWriter = new JsonWriter();
        final Gson gson = new Gson();

        //Execution
        // - Input
        List<Item> items = inputManager.getList(gson, input_example_1());
        // - Calculate
        Output output = engine.calculate(items);
        // - Output
        String outputString = jsonWriter.write(gson,output);
        // - Print
        System.out.println(outputString);


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

    private static TaxCalculator CreateTaxCalculator(){
        final List<ItemType> taxException = new ArrayList<>();
        taxException.add(ItemType.FOOD);
        taxException.add(ItemType.BOOK);
        taxException.add(ItemType.MEDICAL);

        final TaxCalculator tc = new TaxCalculator(
                new UpFiveCentRounder(),
                taxException,
                new BigDecimal("0.10"),
                new BigDecimal("0.05")
        );

        return tc;
    }
}
