package persistence;

import model.AuctioningList;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            AuctioningList al = new AuctioningList("Jason");
            JsonWriter writer = new JsonWriter("./data/\0invaliddestination.json");
            writer.open();
            fail("File shouldn't have been found");
        } catch (IOException e) {
            // expected behaviour
        }
    }

    @Test
    public void testWriterEmptyAuctioningList() {
        try {
            AuctioningList al = new AuctioningList("Jason");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAuctioningList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAuctioningList.json");
            al = reader.read();
            assertEquals("Jason", al.getName());
            assertEquals(0, al.getList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterAuctioningList() {
        try {
            AuctioningList al = new AuctioningList("Jason");
            al.addItem(new Item("Guitar", 100, 30 ,250));
            al.addItem(new Item("Doll", 20, 5, 50));
            JsonWriter writer = new JsonWriter("./data/testWriterAuctioningList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterAuctioningList.json");
            al = reader.read();
            assertEquals("Jason", al.getName());
            List<Item> items = al.getList();
            assertEquals(2, al.getList().size());
            checkItem("Guitar", 100.0, 30.0, 250.0, items.get(0));
            checkItem("Doll", 20.0, 5.0, 50.0, items.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
