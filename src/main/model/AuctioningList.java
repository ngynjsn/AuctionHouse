package model;

// Represents the auctioning list that is used in the AuctionHouse.

import java.util.ArrayList;
import java.util.List;

public class AuctioningList {
    private List<Item> list;

    public AuctioningList() {
        list = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an item, i, into the auctioning list
    public void addItem(Item i) {
        list.add(i);
    }

    // REQUIRES: item with name, n, needs to be already in the list
    // MODIFIES: this
    // EFFECTS: removes an item with the given name n
    public boolean removeItem(String n) {
        for (Item next : list) {
            if (next.getName().equals(n)) {
                list.remove(next);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: list is non empty
    // EFFECTS: returns the first item on list
    public Item getFirstItem() {
        return list.get(0);
    }


    // getters:

    public List<Item> viewItems() {
        return list;
    }

}
