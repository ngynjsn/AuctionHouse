package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, Double initPrice, Double bidIncrement,
                             Double buyOut, Item item) {
        assertEquals(name, item.getName());
        assertEquals(initPrice, item.getInitialPrice());
        assertEquals(bidIncrement, item.getBidIncrement());
        assertEquals(buyOut, item.getBuyOut());
    }
}
