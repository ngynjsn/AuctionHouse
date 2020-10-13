package model;

// Test for AuctioningList

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AuctionHouse;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuctioningListTest {

    Item testItem;
    AuctioningList testList;

    @BeforeEach
    public void setUp() {
        testItem = new Item("Guitar", 100, 50, 500);
        testList = new AuctioningList();
    }

    @Test
    public void testAddItem() {
        AuctioningList testList = new AuctioningList();
        testItem = new Item("Guitar", 100, 50, 500);
        testList.addItem(testItem);
        assertEquals(1, testList.viewItems().size());
        testList.addItem(testItem);
        assertEquals(2, testList.viewItems().size());
    }

    @Test
    public void testRemoveItemNoItems() {
        AuctioningList testList = new AuctioningList();
        testItem = new Item("Guitar", 100, 50, 500);
        assertFalse(testList.removeItem("Jason"));
    }

    @Test
    public void testRemoveItemWithItem() {
        AuctioningList testList = new AuctioningList();
        testItem = new Item("Guitar", 100, 50, 500);
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testList.addItem(testItem);
        testList.addItem(itemTwo);

        assertTrue(testList.removeItem("Doll"));
        assertEquals(1, testList.viewItems().size());
    }

    @Test
    public void testGetFirstItem() {
        AuctioningList testList = new AuctioningList();
        testItem = new Item("Guitar", 100, 50, 500);
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testList.addItem(testItem);
        testList.addItem(itemTwo);

        assertEquals("Guitar", testList.getFirstItem().getName());
    }
}
