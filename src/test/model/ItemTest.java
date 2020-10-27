package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for Item class

class ItemTest {

    private Item testItem;

    @BeforeEach
    public void setUp() {
        testItem = new Item("Guitar", 100, 50, 400);
    }

    // this test tests all the getters
    @Test
    public void testItemConstructor() {
        assertEquals(testItem.getName(), "Guitar");
        assertEquals(testItem.getInitialPrice(), 100);
        assertEquals(testItem.getBidIncrement(), 50);
        assertEquals(testItem.getBuyOut(), 400);
        assertEquals(testItem.getCurrentPrice(), 100);
        assertEquals(testItem.getBidCount(), 0);
    }

    @Test
    public void testGetBuyer() {
        testItem.setBuyer("John");
        assertEquals(testItem.getBuyer(), "John");
    }

    @Test
    public void testSetCurrentPrice() {
        testItem.setCurrentPrice(150);
        assertEquals(testItem.getCurrentPrice(), 150);
    }

    @Test
    public void testIncrementBidCount() {
        assertEquals(0, testItem.getBidCount());
        testItem.incrementBidCount();
        assertEquals(1, testItem.getBidCount());
    }

}