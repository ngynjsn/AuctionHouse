package ui;

import model.AuctioningList;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for AuctionHouse

public class AuctionHouseTest {

    Item testItem;
    AuctioningList testList;
    AuctionHouse testHouse;

    @BeforeEach
    public void setUp() {
        testHouse = new AuctionHouse("Jason");
        testList = new AuctioningList();
        testItem = new Item("Guitar", 100, 50, 500);
    }

    @Test
    public void testSetCurrentItem() {
        testItem = new Item("Guitar", 100, 50, 500);
        testHouse.setCurrentItem(testItem);
        assertEquals("Guitar", testHouse.getCurrentItem().getName());
    }
}
