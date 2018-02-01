package com.abc.project.io;

import com.abc.project.model.Item;
import org.junit.Test;
import java.math.BigDecimal;
import static junit.framework.TestCase.assertTrue;

public class ItemInputManagerTest {
    final String jsonInput = "";

    //Array

    @Test
    public void getArray_fromJsonString_arrayNotNull(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        //Execute
        Item[] items = inputManager.getArray(jsonInput);

        //Verify
        assertTrue(items != null);
    }

    @Test
    public void getArray_fromJsonString_arrayCountGreaterThanZero(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        Item[] items = inputManager.getArray(jsonInput);

        //Verify
        assertTrue(items.length > 0);
    }


    //Price

    @Test
    public void getArray_fromJsonString_firstElementContainsPrice(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        Item[] items = inputManager.getArray(jsonInput);

        //Verify
        Item firstItem = items[0];
        assertTrue(firstItem.getPrice() !=  null);
    }

    @Test
    public void getArray_fromJsonString_firstElementPriceAsExpected(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();
        BigDecimal expectedPrice = new BigDecimal(10.00);

        //Execute
        Item[] items = inputManager.getArray(jsonInput);

        //Verify
        Item firstItem = items[0];
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

    //Imported
    @Test
    public void getArray_fromJsonString_firstElementIsNotImported(){
        //Setup
        final ItemsInputManager inputManager = new ItemsInputManager();

        //Execute
        Item[] items = inputManager.getArray(jsonInput);

        //Verify
        Item firstItem = items[0];
        assertTrue(firstItem.isImported());
    }

    //Name
    



}
