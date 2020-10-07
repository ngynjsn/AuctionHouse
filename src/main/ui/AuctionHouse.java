package ui;

import model.Item;

import java.util.List;

// THE APP TO BEGIN THE AUCTION HOUSE

public class AuctionHouse {
    private List<Item> auctioningList;
    private String seller;

    // MODIFIES: this
    // EFFECTS: adds an item, i, into the auctioning list
    public void addItem(Item i) {
        auctioningList.add(i);
    }

    public void removeItem(Item i) {
        auctioningList.remove(i);
    }

    // getters:

    public List<Item> viewItems() {
        return auctioningList;
    }

    public String getSeller() {
        return seller;
    }
}
