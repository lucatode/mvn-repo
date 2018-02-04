package com.abc.project.io;


import com.abc.project.builder.Builder;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;
import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GsonOutputManagerTest {

    @Test
    public void write_Output_String(){
        //Setup
        GsonOutputManager jw = new GsonOutputManager(new Gson());
        Output o = Builder.output().build();
        String expectedString = "{\"items\":[],\"total\":0.00,\"taxes\":0.00}";

        //Execute
        String jsonString = jw.write(o);

        //Verify
        assertEquals(expectedString, jsonString);
    }

    @Test
    public void write_OutputWithOneItem_StringContainsItem(){
        //Setup
        GsonOutputManager jw = new GsonOutputManager(new Gson());
        Output o = Builder.output()
                .addItem(Builder.item().build())
                .build();
        String expectedString = "{\"items\":[{\"price\":10.00,\"quantity\":1,\"imported\":false,\"name\":\"Item\",\"type\":\"STANDARD\"}],\"total\":0.00,\"taxes\":0.00}";

        //Execute
        String jsonString = jw.write(o);

        //Verify
        assertEquals(expectedString, jsonString);
    }

    @Test
    public void write_OutputWithOneItemFOOD_StringContainsFOOD(){
        //Setup
        GsonOutputManager jw = new GsonOutputManager(new Gson());
        Output o = Builder.output()
                .addItem(Builder.item().setType(ItemType.FOOD).build())
                .build();
        String expectedString = "{\"items\":[{\"price\":10.00,\"quantity\":1,\"imported\":false,\"name\":\"Item\",\"type\":\"FOOD\"}],\"total\":0.00,\"taxes\":0.00}";

        //Execute
        String jsonString = jw.write(o);

        //Verify
        assertEquals(expectedString, jsonString);
    }

    @Test
    public void write_OutputWithTotal_StringContainsTotal(){
        //Setup
        GsonOutputManager jw = new GsonOutputManager(new Gson());
        Output o = Builder.output()
                .addItem(Builder.item().setType(ItemType.FOOD).build())
                .setTotal(new BigDecimal("10.00"))
                .build();
        String expectedString = "{\"items\":[{\"price\":10.00,\"quantity\":1,\"imported\":false,\"name\":\"Item\",\"type\":\"FOOD\"}],\"total\":10.00,\"taxes\":0.00}";

        //Execute
        String jsonString = jw.write(o);

        //Verify
        assertEquals(expectedString, jsonString);
    }

    @Test
    public void write_OutputWithTaxes_StringContainsTotal(){
        //Setup
        GsonOutputManager jw = new GsonOutputManager(new Gson());
        Output o = Builder.output()
                .addItem(Builder.item().setType(ItemType.FOOD).build())
                .setTaxes(new BigDecimal("1.00"))
                .build();
        String expectedString = "{\"items\":[{\"price\":10.00,\"quantity\":1,\"imported\":false,\"name\":\"Item\",\"type\":\"FOOD\"}],\"total\":0.00,\"taxes\":1.00}";

        //Execute
        String jsonString = jw.write(o);

        //Verify
        assertEquals(expectedString, jsonString);
    }
}
