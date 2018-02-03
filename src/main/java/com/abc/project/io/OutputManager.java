package com.abc.project.io;

import com.google.gson.Gson;

public interface OutputManager<T>{
     String write(Gson gson, T element);
}
