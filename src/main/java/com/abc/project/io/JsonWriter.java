package com.abc.project.io;

import com.abc.project.model.Output;
import com.google.gson.Gson;

public class JsonWriter implements OutputManager<Output> {

    public String write(Gson gson, Output element) {
        String jsonString = gson.toJson(element);
        return jsonString;
    }
}
