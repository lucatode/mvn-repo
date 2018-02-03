package com.abc.project.io;

import com.abc.project.model.Item;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ItemsInputManager {

    public List<Item> getList(Gson gson, String jsonString) {
        Item[] items = gson.fromJson(jsonString, Item[].class);
        List<Item> list = Arrays.asList(items);
        return list;
    }

}
