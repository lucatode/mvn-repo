package com.abc.project.io;

import com.abc.project.model.Output;
import com.google.gson.Gson;

public class GsonOutputManager implements OutputManager<Output> {

    private final Gson gson;

    public GsonOutputManager(Gson gson){
        this.gson = gson;
    }

    public String write(Output element) {
        String jsonString = this.gson.toJson(element);
        return jsonString;
    }

}
