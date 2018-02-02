package com.abc.project.io;

import com.abc.project.model.Item;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ItemsInputManager implements InputManager<Item> {

    private final Gson gson;

    public ItemsInputManager(Gson gson){
        this.gson = gson;
    }

    public List<Item> getList(String jsonString) {
        Item[] items = this.gson.fromJson(jsonString, Item[].class);
        List<Item> list = Arrays.asList(items);
        return list;
    }

}
