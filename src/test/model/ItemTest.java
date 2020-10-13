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

    @Test
    public void testSetCurrentPrice() {
        Item testItem= new Item("Guitar", 100, 50, 400);
        testItem.setCurrentPrice(150);
        assertEquals(testItem.getCurrentPrice(), 150);
    }

    @Test
    public void testIncrementBidCount() {
        Item testItem= new Item("Guitar", 100, 50, 400);
        assertEquals(0, testItem.getBidCount());
        testItem.incrementBidCount();
        assertEquals(1, testItem.getBidCount());
    }

}