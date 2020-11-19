package model;

// Test for AuctioningList

import exceptions.EmptyListException;
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
        testList.addItem(testItem);
        assertEquals(1, testList.getList().size());
        Item testItem2 = new Item("Doll", 30, 50, 100);
        testList.addItem(testItem2);
        assertEquals(2, testList.getList().size());
        assertEquals(testList.getList().get(0), testItem);
        assertEquals(testList.getList().get(1), testItem2);
    }

    @Test
    public void testRemoveItemNoItems() {
        assertFalse(testList.removeItem("Jason"));
    }

    @Test
    public void testRemoveItemWithItem() {
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testList.addItem(testItem);
        testList.addItem(itemTwo);

        assertTrue(testList.removeItem("Doll"));
        assertEquals(1, testList.getList().size());
        assertTrue(testList.removeItem("Guitar"));
        assertEquals(0, testList.getList().size());
    }

    @Test
    public void testGetFirstItemNoException() {
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testList.addItem(testItem);
        testList.addItem(itemTwo);

        try {
            testList.getFirstItem();
            assertEquals(testList.getFirstItem(), testItem);
        } catch (EmptyListException e) {
            fail("Exception should not have been thrown here");
        }
    }

    @Test
    public void testGetFirstItemException() {
        assertTrue(testList.getList().isEmpty());

        try {
            testList.getFirstItem();
            fail("Exception should have been thrown");
        } catch (EmptyListException e) {
            // expected behaviour
        }
    }
}
