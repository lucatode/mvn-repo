package com.abc.project.io;

import com.abc.project.model.Item;

import java.math.BigDecimal;

public class ItemsInputManager implements InputManager<Item> {

    public Item[] getArray(String jsonString) {
        BigDecimal d = new BigDecimal(10.00);
        Item[] array = new Item[1];
        array[0] = new Item(d, imported);

        return array;
    }

}
