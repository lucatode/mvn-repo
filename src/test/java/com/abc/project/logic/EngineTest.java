package com.abc.project.logic;

import com.abc.project.model.Item;
import com.abc.project.model.ItemType;
import com.abc.project.model.Output;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class EngineTest {

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static class ItemBuilder {

        private BigDecimal price = new BigDecimal("10.00");
        private ItemType type = ItemType.STANDARD;
        private boolean imported = false;
        private String name = "Item";

        public ItemBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder setPrice(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public ItemBuilder setImported(final boolean imported) {
            this.imported = imported;
            return this;
        }

        public ItemBuilder setType(ItemType type) {
            this.type = type;
            return this;
        }

        public Item build() {
            return new Item(price, imported, name, type);
        }


    }

    //Output
    @Test
    public void calculate_Items_OutputNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output != null);
    }

    //Total
    @Test
    public void calculate_Items_OutputTotalNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTotal() != null);
    }

    @Test
    public void calculate_OneItem_TotalEqualsItemPrice(){

    }

    //Tax
    @Test
    public void calculate_Items_OutputTaxNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getTax() != null);
    }

    //Items
    @Test
    public void calculate_Items_OutputItemListNotNull(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getItems() != null);
    }

    @Test
    public void calculate_OneItemList_OneElementInOutputItemList(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add(builder().build());

        //Execute
        Output output = engine.calculate(items);

        //Verify
        assertTrue(output.getItems().size() == 1);
    }

    @Test //Input: { 14.99, false, STANDARD } - Expected output price: 16.49
    public void calculate_OneItemList_OutputItemPriceUpdated(){
        //Setup
        Engine engine = new Engine();
        List<Item> items = new ArrayList<Item>();
        items.add( builder()
                .setPrice(new BigDecimal("14.99"))
                .setImported(false)
                .setType(ItemType.STANDARD)
                .build());
        BigDecimal expectedPrice = new BigDecimal("16.49");

        //Execute
        Output output = engine.calculate(items);

        //Verify
        Item firstItem = output.getItems().get(0);
        assertTrue(firstItem.getPrice().equals(expectedPrice));
    }

}
