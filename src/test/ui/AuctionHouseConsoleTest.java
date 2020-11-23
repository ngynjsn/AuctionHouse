package ui;

import model.AuctioningList;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for AuctionHouse

public class AuctionHouseConsoleTest {

    Item testItem;
    AuctioningList testList;
    AuctionHouseConsole testHouse;

    @BeforeEach
    public void setUp() {
        testHouse = new AuctionHouseConsole("Jason");
        testList = new AuctioningList("Jason's List");
        testItem = new Item("Guitar", 100, 50, 500);
    }

    @Test
    public void testSetCurrentItem() {
        testItem = new Item("Guitar", 100, 50, 500);
        testHouse.setCurrentItem(testItem);
        assertEquals("Guitar", testHouse.getCurrentItem().getName());
    }
}
