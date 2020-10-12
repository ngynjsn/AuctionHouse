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

    // this test 
    @Test
    public void testItemConstructor() {
        assertEquals(testItem.getName(), "Guitar");
    }


}