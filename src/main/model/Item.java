package model;

// CLASS TO REPRESENT ITEMS IN THE AUCTIONING LIST

public class Item {
    private String name;
    private double initialPrice;
    private double bidIncrement;
    private double buyOut;
    private double currentPrice;
    private String buyer;


    // EFFECTS: constructs an item with name, initial price, bid increments,
    //          and a buy out price
    public Item(String name, double initialPrice, double bidIncrement,
                double buyOut) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.bidIncrement = bidIncrement;
        this.buyOut = buyOut;
        this.currentPrice = initialPrice;
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

    public void setBuyer(String n) {
        buyer = n;
    }
}
