package com.abc.project.io;

import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.google.gson.Gson;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ItemInputManagerTest {
    final String jsonInput = "[{ \"price\":10.00, \"imported\": false, \"name\":\"Item Name\", \"type\":\"STANDARD\" }]";
    final String jsonInputFood = "[{ \"price\":10.00, \"imported\": false, \"name\":\"Item Name\", \"type\":\"FOOD\" }]";
    final String jsonInputMedical = "[{ \"price\":10.00, \"imported\": false, \"name\":\"Item Name\", \"type\":\"MEDICAL\" }]";
    final String jsonInputBook = "[{ \"price\":10.00, \"imported\": false, \"name\":\"Item Name\", \"type\":\"BOOK\" }]";
    final String jsonMultipleItemInput = "[{ \"price\":10.00, \"imported\": false, \"name\":\"Item 1\", \"type\":\"STANDARD\" }," +
            "{ \"price\":5.00, \"imported\": false, \"name\":\"Item 2\", \"type\":\"STANDARD\" }]";

    final Gson gson = new Gson();

    //List
    @Test
    public void getList_fromJsonString_ListNotNull(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        assertTrue(items != null);
    }

    @Test
    public void getList_fromJsonString_ListCountGreaterThanZero(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        assertTrue(items.size() > 0);
    }

    //Price
    @Test
    public void getList_fromJsonString_firstElementContainsPrice(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getPrice() !=  null);
    }

    @Test
    public void getList_fromJsonString_firstElementPriceAsExpected(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        BigDecimal expectedPrice = new BigDecimal("10.00");

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    //Imported
    @Test
    public void getList_fromJsonString_firstElementIsNotImported(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(!firstItem.isImported());
    }

    //Name
    @Test
    public void getList_fromJsonString_firstElementNameAsExpected(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        final String expectedName = "Item Name";

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getName().equals(expectedName));
    }

    //Type
    @Test
    public void getList_fromJsonString_firstElementTypeSTANDARD(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        final ItemType expectedType = ItemType.STANDARD;

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    @Test
    public void getList_fromJsonString_firstElementTypeFOOD(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        final ItemType expectedType = ItemType.FOOD;

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInputFood);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    @Test
    public void getList_fromJsonString_firstElementTypeMEDICAL(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        final ItemType expectedType = ItemType.MEDICAL;

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInputMedical);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    @Test
    public void getList_fromJsonString_firstElementTypeBOOK(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        final ItemType expectedType = ItemType.BOOK;

        //Execute
        List<Item> items = inputManager.getList(gson, jsonInputBook);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    //Multiple item array
    @Test
    public void getList_fromJsonString_ListSizeEqualsTwo(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(gson, jsonMultipleItemInput);

        //Verify
        assertTrue(items.size() == 2);
    }


}
