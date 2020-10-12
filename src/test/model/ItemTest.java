package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item testItem;

    @BeforeEach
    public void setUp() {
        Item testItem = new Item("Guitar", 100, 50, 400);
    }

    // this test tests all the getters
    @Test
    public void testItemConstructor() {
        Item testItem = new Item("Guitar", 100, 50, 400);
        assertEquals(testItem.getName(), "Guitar");
        assertEquals(testItem.getInitialPrice(), 100);
        assertEquals(testItem.getBidIncrement(), 50);
        assertEquals(testItem.getBuyOut(), 400);
        assertEquals(testItem.getCurrentPrice(), 100);
        assertEquals(testItem.getBidCount(), 0);
    }

    @Test
    public void testGetBuyer() {
        Item testItem = new Item("Guitar", 100, 50, 400);
        testItem.setBuyer("John");
        assertEquals(testItem.getBuyer(), "John");
    }



}