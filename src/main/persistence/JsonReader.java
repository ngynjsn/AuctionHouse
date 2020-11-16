package persistence;

import model.AuctioningList;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads AuctioningList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads auctioning list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AuctioningList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAuctioningList(jsonObject);
    }

    // EFFECTS: parses auctioning list from JSON object and returns it
    private AuctioningList parseAuctioningList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        AuctioningList al = new AuctioningList(name);
        addItems(al, jsonObject);
        return al;
    }

    // EFFECTS: adds items into file in form of JSON
    private void addItems(AuctioningList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(al, nextItem);
        }
    }

    // EFFECTS: adds item into list in form of JSON
    private void addItem(AuctioningList al, JSONObject jsonObject) {
        String name = jsonObject.getString("item name");
        double initPrice = Double.parseDouble(jsonObject.getString("initial price"));
        double bidIncrement = Double.parseDouble(jsonObject.getString("bid increment"));
        double buyOut = Double.parseDouble(jsonObject.getString("buy out"));
        Item item = new Item(name, initPrice, bidIncrement, buyOut);
        al.addItem(item);
    }


    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
