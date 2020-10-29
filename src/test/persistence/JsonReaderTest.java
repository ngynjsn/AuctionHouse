package persistence;

import model.AuctioningList;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNoFile() {
        JsonReader reader = new JsonReader("./data/fakeFile.json");
        try {
            AuctioningList al = reader.read();
        } catch (IOException e) {
            // expected behaviour
        }
    }

    @Test
    public void testReaderEmptyAuctioningList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAuctioningList.json");
        try {
            AuctioningList al = reader.read();
            assertEquals("Jason", al.getName());
            assertEquals(0, al.getList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderAuctioningList() {
        JsonReader reader = new JsonReader("./data/testReaderAuctioningList.json");
        try {
            AuctioningList al = reader.read();
            assertEquals("Jason", al.getName());
            List<Item> items = al.getList();
            assertEquals(2, al.getList().size());
            checkItem("Guitar", 100.0, 30.0, 250.0, items.get(0));
            checkItem("Doll", 20.0, 5.0, 50.0, items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
