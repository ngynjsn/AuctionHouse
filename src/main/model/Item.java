package model;

// Represents an item that can be used in an auction house. It has a name, initial price, bid increments, bid count,
// buy out price, current price, and the name of the buyer of the item

public class Item {
    private String name;
    private double initialPrice;
    private double bidIncrement;
    private int bidCount;
    private double buyOut;
    private double currentPrice;
    private String buyer;

    // REQUIRES: buyOut price is greater than initialPrice
    // EFFECTS: constructs an item with name, initial price, bid increments,
    //          , a buy out price. Begins with 0 bid count and current price is initial price
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

    public String getName() {
        return name;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public double getBidIncrement() {
        return bidIncrement;
    }

    public double getBuyOut() {
        return buyOut;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getBuyer() {
        return buyer;
    }

    public int getBidCount() {
        return bidCount;
    }

    // setters:

    public void setBuyer(String n) {
        buyer = n;
    }

    public void setCurrentPrice(double price) {
        currentPrice = price;
    }


}
