package com.abc.project.io;

import com.abc.project.model.Item;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class GsonInputManager implements InputManager<Item>{

    private final Gson gson;

    public GsonInputManager(Gson gson){
        this.gson = gson;
    }

    public List<Item> getList(String str) {
        Item[] items = this.gson.fromJson(str, Item[].class);
        List<Item> list = Arrays.asList(items);
        return list;
    }

}
