package persistence;

import model.AuctioningList;
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
        return null; //STUB
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
