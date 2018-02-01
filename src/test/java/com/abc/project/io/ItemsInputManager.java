package com.abc.project.io;

import com.abc.project.model.Item;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ItemsInputManager {

    final String jsonInput = "";

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
        assertTrue(items != null);
    }







}
