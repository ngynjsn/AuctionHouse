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
        AuctionHouse testHouse = new AuctionHouse();
        testItem = new Item("Guitar", 100, 50, 500);
    }

    @Test
    public void testAddItem() {
        testHouse.addItem(testItem);
        assertEquals(1, testHouse.viewItems().size());
        testHouse.addItem(testItem);
        assertEquals(2, testHouse.viewItems().size());
    }

    @Test
    public void testRemoveItemNoItems() {
        assertFalse(testHouse.removeItem("Jason"));
    }

    @Test
    public void testRemoveItemWithItem() {
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testHouse.addItem(testItem);
        testHouse.addItem(itemTwo);

        assertTrue(testHouse.removeItem("Doll"));
        assertEquals(1, testHouse.viewItems().size());
    }

    @Test
    public void testGetFirstItem() {
        Item itemTwo = new Item("Doll", 10, 5, 30);
        testHouse.addItem(testItem);
        testHouse.addItem(itemTwo);

        assertEquals("Doll", testHouse.getFirstItem().getName());
    }

    @Test
    public void testSetSeller() {
        testHouse.setSeller("Jason");
        assertEquals(testHouse.getSeller(), "Jason");
    }

    @Test
    public void testSetCurrentItem() {
        testHouse.setCurrentItem(testItem);
        assertEquals("Guitar", testHouse.getCurrentItem());
    }
}
