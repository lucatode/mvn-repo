package com.abc.project.io;

import com.abc.project.builder.Builder;
import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.google.gson.Gson;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class GsonInputManagerTest {
    final String jsonInput = Builder.item().toJsonArray();
    final String jsonInputFood = Builder.item().setType(ItemType.FOOD).toJsonArray();
    final String jsonInputBook = Builder.item().setType(ItemType.BOOK).toJsonArray();
    final String jsonInputMedical = Builder.item().setType(ItemType.MEDICAL).toJsonArray();
    final String jsonMultipleItemInput = "["+Builder.item().toJson()+"," +Builder.item().setPrice("5.00").setName("Item 2").toJson()+"]";

    final Gson gson = new Gson();

    //List
    @Test
    public void getList_fromJsonString_ListNotNull(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        assertTrue(items != null);
    }

    @Test
    public void getList_fromJsonString_ListCountGreaterThanZero(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        assertTrue(items.size() > 0);
    }

    //Price
    @Test
    public void getList_fromJsonString_firstElementContainsPrice(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getPrice() !=  null);
    }

    @Test
    public void getList_fromJsonString_firstElementPriceAsExpected(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        BigDecimal expectedPrice = new BigDecimal("10.00");

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
        final GsonInputManager inputManager = new GsonInputManager(gson);

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertFalse(firstItem.isImported());
    }

    //Name
    @Test
    public void getList_fromJsonString_firstElementNameAsExpected(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        final String expectedName = "Item";

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertEquals(expectedName, firstItem.getName());
    }

    //Type
    @Test
    public void getList_fromJsonString_firstElementTypeSTANDARD(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        final ItemType expectedType = ItemType.STANDARD;

        //Execute
        List<Item> items = inputManager.getList(jsonInput);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    @Test
    public void getList_fromJsonString_firstElementTypeFOOD(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        final ItemType expectedType = ItemType.FOOD;

        //Execute
        List<Item> items = inputManager.getList(jsonInputFood);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    @Test
    public void getList_fromJsonString_firstElementTypeMEDICAL(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        final ItemType expectedType = ItemType.MEDICAL;

        //Execute
        List<Item> items = inputManager.getList(jsonInputMedical);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    @Test
    public void getList_fromJsonString_firstElementTypeBOOK(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);
        final ItemType expectedType = ItemType.BOOK;

        //Execute
        List<Item> items = inputManager.getList(jsonInputBook);

        //Verify
        Item firstItem = items.get(0);
        assertTrue(firstItem.getType() == expectedType);
    }

    //Multiple item array
    @Test
    public void getList_fromJsonString_ListSizeEqualsTwo(){
        //Setup
        final GsonInputManager inputManager = new GsonInputManager(gson);

        //Execute
        List<Item> items = inputManager.getList( jsonMultipleItemInput);

        //Verify
        assertTrue(items.size() == 2);
    }


}
