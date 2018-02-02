package com.abc.project.io;

import com.abc.project.model.Item;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ItemInputManagerTest {
    final String jsonInput = "";

    //List
    @Test
    public void getList_fromJsonString_ListNotNull(){
        //Setup
        final InputManager<Item> inputManager = new ItemsInputManager();
        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        assertTrue(items != null);
    }

    @Test
    public void getList_fromJsonString_ListCountGreaterThanZero(){
        //Setup
        final InputManager<Item> inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        assertTrue(items.size() > 0);
    }

    //Price
    @Test
    public void getList_fromJsonString_firstElementContainsPrice(){
        //Setup
        final InputManager<Item> inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getPrice() !=  null);
    }

    @Test
    public void getList_fromJsonString_firstElementPriceAsExpected(){
        //Setup
        final InputManager<Item> inputManager = new ItemsInputManager();
        BigDecimal expectedPrice = new BigDecimal(10.00);

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    //Imported
    @Test
    public void getList_fromJsonString_firstElementIsNotImported(){
        //Setup
        final InputManager<Item> inputManager = new ItemsInputManager();

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(!firstItem.isImported());
    }

    //Name
    @Test
    public void getList_fromJsonString_firstElementNameAsExpected(){
        //Setup
        final InputManager<Item> inputManager = new ItemsInputManager();
        final String expectedName = "Item Name";

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getName() == expectedName);
    }



}
