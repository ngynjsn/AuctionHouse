package ui;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionHouseTest {

    Item testItem;
    AuctionHouse testHouse;

    @BeforeEach
    public void setUp() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
    }

    @Test
    public void testAddItem() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
        testHouse.addItem(testItem);
        assertEquals(1, testHouse.viewItems().size());
        testHouse.addItem(testItem);
        assertEquals(2, testHouse.viewItems().size());
    }

    @Test
    public void testRemoveItemNoItems() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
        assertFalse(testHouse.removeItem("Jason"));
    }

    @Test
    public void testRemoveItemWithItem() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testHouse.addItem(testItem);
        testHouse.addItem(itemTwo);

        assertTrue(testHouse.removeItem("Doll"));
        assertEquals(1, testHouse.viewItems().size());
    }

    @Test
    public void testGetFirstItem() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testHouse.addItem(testItem);
        testHouse.addItem(itemTwo);

        assertEquals("Guitar", testHouse.getFirstItem().getName());
    }

    @Test
    public void testSetSeller() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
        testHouse.setSeller("John");
        assertEquals(testHouse.getSeller(), "John");
    }

    @Test
    public void testSetCurrentItem() {
        AuctionHouse testHouse = new AuctionHouse("Jason");
        testItem = new Item("Guitar", 100, 50, 500);
        testHouse.setCurrentItem(testItem);
        assertEquals("Guitar", testHouse.getCurrentItem().getName());
    }
}
