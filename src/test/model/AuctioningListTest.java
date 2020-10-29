package model;

// Test for AuctioningList

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuctioningListTest {

    private Item testItem;
    private AuctioningList testList;

    @BeforeEach
    public void setUp() {
        testItem = new Item("Guitar", 100, 50, 500);
        testList = new AuctioningList("Jason");
    }

    @Test
    public void testAddItem() {
        testItem = new Item("Guitar", 100, 50, 500);
        testList.addItem(testItem);
        assertEquals(1, testList.getList().size());
        testList.addItem(testItem);
        assertEquals(2, testList.getList().size());
    }

    @Test
    public void testRemoveItemNoItems() {
        testItem = new Item("Guitar", 100, 50, 500);
        assertFalse(testList.removeItem("Jason"));
    }

    @Test
    public void testRemoveItemWithItem() {
        testItem = new Item("Guitar", 100, 50, 500);
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testList.addItem(testItem);
        testList.addItem(itemTwo);

        assertTrue(testList.removeItem("Doll"));
        assertEquals(1, testList.getList().size());
    }

    @Test
    public void testGetFirstItem() {
        testItem = new Item("Guitar", 100, 50, 500);
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testList.addItem(testItem);
        testList.addItem(itemTwo);

        assertEquals("Guitar", testList.getFirstItem().getName());
    }
}
