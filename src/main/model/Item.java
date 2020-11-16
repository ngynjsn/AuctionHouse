package model;

// Represents an item that can be used in an auction house. It has a name, initial price, bid increments, bid count,
// buy out price, current price, and the name of the buyer of the item

import org.json.JSONObject;
import persistence.Writable;

public class Item implements Writable {
    private String name;
    private double initialPrice;
    private double bidIncrement;
    private int bidCount;
    private double buyOut;
    private double currentPrice;
    private String buyer;

    // REQUIRES: buyOut price is greater than initialPrice
    // EFFECTS: constructs an item with name, initial price, bid increments,
    //          and a buy out price. Begins with 0 bid count and current price is initial price
    public Item(String name, double initialPrice, double bidIncrement,
                double buyOut) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.bidIncrement = bidIncrement;
        this.bidCount = 0;
        this.buyOut = buyOut;
        this.currentPrice = initialPrice;
    }

    // MODIFIES: this
    // EFFECTS: increments the bid count by 1
    public void incrementBidCount() {
        bidCount += 1;
    }

    // getters:

    //  EFFECTS: returns name of item
    public String getName() {
        return name;
    }

    // EFFECTS: returns initial price of item
    public double getInitialPrice() {
        return initialPrice;
    }

    // EFFECTS: returns bid increment of item
    public double getBidIncrement() {
        return bidIncrement;
    }

    // EFFECTS; returns buy out price of item
    public double getBuyOut() {
        return buyOut;
    }

    // EFFECTS: returns current price of item
    public double getCurrentPrice() {
        return currentPrice;
    }

    // EFFECTS: returns buyer of this item
    public String getBuyer() {
        return buyer;
    }

    // EFFECTS: returns total bid count of item
    public int getBidCount() {
        return bidCount;
    }

    // setters:

    // EFFECTS: sets buyer field to given name, n
    public void setBuyer(String n) {
        buyer = n;
    }

    // EFFECTS: sets current price to given price
    public void setCurrentPrice(double price) {
        currentPrice = price;
    }

    // EFFECTS: converts item into JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("item name", name);
        json.put("initial price", initialPrice + "");
        json.put("bid increment", bidIncrement + "");
        json.put("buy out", buyOut + "");
        return json;
    }
}
