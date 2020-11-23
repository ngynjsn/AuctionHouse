package model;

// Represents the auctioning list that is used in the AuctionHouse.

import exceptions.EmptyListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class AuctioningList implements Writable {
    private String name;
    private List<Item> list;

    // EFFECTS: construct an auctioning list which is empty and a name designated to the person selling
    //          these items
    public AuctioningList(String name) {
        this.name = name;
        list = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an item, i, into the auctioning list
    public void addItem(Item i) {
        list.add(i);
    }

    // MODIFIES: this
    // EFFECTS: removes an item with the given name n if found and returns true. Else, return false.
    public boolean removeItem(String n) {
        for (Item next : list) {
            if (next.getName().equals(n)) {
                list.remove(next);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the first item on list
    //          throws EmptyListException if the list is empty
    public Item getFirstItem() throws EmptyListException {
        if (list.isEmpty()) {
            throw new EmptyListException();
        } else {
            return list.get(0);
        }
    }

    // getters:

    // EFFECTS: returns list
    public List<Item> getList() {
        return list;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: stores items into JSON form
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: converts items from auctioning list into JSON form (array)
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : list) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
