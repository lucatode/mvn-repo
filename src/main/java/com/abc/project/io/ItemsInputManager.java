package com.abc.project.io;

import com.abc.project.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemsInputManager implements InputManager<Item> {

    public List<Item> getList(String jsonString) {

        List<Item> list = new ArrayList<Item>();
        BigDecimal d = new BigDecimal(10.00);
        Item item = new Item(d, false, "Item Name");
        list.add(item);

        return list;
    }

}
