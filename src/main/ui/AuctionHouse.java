package ui;


import model.AuctioningList;
import model.Item;

import java.util.Scanner;

// Auction house application that is used to begin adding items into an auction list, removing items, viewing items,
// and can begin the auctioning process.

public class AuctionHouse {
    private AuctioningList auctioningList;
    private Item currentItem;
    private double currentProfit = 0;
    private Scanner input;

    // EFFECTS: constructor to run the auction house
    public AuctionHouse() {
        runAuctionHouse();
    }

    // EFFECTS: constructor to help with tests
    //          creates an empty auctioning list
    public AuctionHouse(String n) {
        auctioningList = new AuctioningList(n);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAuctionHouse() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // EFFECTS: lists the options available to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add item");
        System.out.println("\tr -> remove item");
        System.out.println("\tv -> view items");
        System.out.println("\tb -> begin auction house");
    }

    // MODIFIES: this
    // EFFECTS: initializes empty list and instantiates scanner
    private void init() {
        String name;
        input = new Scanner(System.in);
        System.out.println("Please type in your name:");
        name = input.next();
        System.out.println("Thank you!");
        auctioningList = new AuctioningList(name);
    }

    // MODIFIES: this
    // EFFECTS: processes user command depending on keywords
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("r")) {
            doRemoveItem();
        } else if (command.equals("v")) {
            doViewList();
        } else if (command.equals("b")) {
            doAuctionHouse();
        }
    }

    // MODIFIES: this
    // EFFECTS: begins the removal of an item. if found, return true statement and remove item.
    //          if item is not found, return false statement and do nothing.
    private void doRemoveItem() {
        System.out.println("What is the name of the item you are trying to remove?");
        String name = input.next();
        if (auctioningList.removeItem(name)) {
            auctioningList.removeItem(name);
            System.out.println("The item has been removed.");
        } else {
            System.out.println("Sorry, cannot find item with given name.");
        }
    }

    // MODIFIES: this
    // EFFECTS: begins the addition of a newly created item.
    private void doAddItem() {
        auctioningList.addItem(createItem());
    }

    // EFFECTS: shows the user the list of item in auctioning list
    private void doViewList() {
        int i = 1;
        for (Item next : auctioningList.getList()) {
            System.out.println(i + ":" + next.getName());
            System.out.println("Initial price: " + next.getInitialPrice());
            System.out.println("Bid increment: " + next.getBidIncrement());
            System.out.println("Buyout price: " + next.getBuyOut());
            i++;
        }
    }

    // EFFECTS: creates an item based on prompts given to user
    private Item createItem() {
        System.out.println("What is the name of your item?");
        String name = input.next();
        System.out.println("What would you like to be the initial price?");
        double initPrice = input.nextDouble();
        System.out.println("What would you like to be the bid increment?");
        double bidPrice = input.nextDouble();
        System.out.println("What would you like to be the buyout offer?");
        double buyOut = input.nextDouble();

        return new Item(name, initPrice, bidPrice, buyOut);
    }

    // REQUIRES: auctioning list is non-empty
    // MODIFIES: this
    // EFFECTS: begins the auction house
    private void doAuctionHouse() {
        System.out.println("Let's get it started, " + auctioningList.getName() + "!");
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            if (auctioningList.getList().isEmpty()) {
                System.out.println("All items have had a chance to be auctioned!");
                System.out.println("Total profit is: " + currentProfit);
                keepGoing = false;
            } else {
                currentItem = auctioningList.getFirstItem();
                System.out.println("Current item for sale is: " + currentItem.getName());
                System.out.println("Initial price is " + currentItem.getInitialPrice() + ". Bidding increments are "
                        + currentItem.getBidIncrement() + ". Buyout price is " + currentItem.getBuyOut()
                        + ". Current price is " + currentItem.getCurrentPrice());
                System.out.println("Would you like to bid for this item? If yes, type 'bid'. Else type 'no'");
                command = input.next();
                command = command.toLowerCase();
                processCommandWithItem(command, currentItem);
            }
        }
    }

    // MODIFIES: this, item
    // EFFECTS: process commands depending on keyword that are given by user
    private void processCommandWithItem(String command, Item i) {
        if (command.equals("bid")) {
            doBidding(i);
        } else if (command.equals("no")) {
            finishItem(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: continues the auction house process by removing first item on the list and moving onto the next.
    //          adds profit from item to currentProfit
    private void finishItem(Item i) {
        if (i.getBidCount() == 0) {
            System.out.println("Item had no bidders");
        } else {
            currentProfit += i.getCurrentPrice();
            System.out.println("Item sold to " + i.getBuyer() + " for " + i.getCurrentPrice());
        }
        auctioningList.removeItem(i.getName());
    }

    // MODIFIES: this, i
    // EFFECTS: processes the bidding functions. Begins bid with initial price and remembers bidder. Increments current
    //          item price by bid amount and remembers bidder. Stops bidding for item once current price exceeds
    //          buy out price and removes from auctioning list also remembering bidder.
    private void doBidding(Item i) {
        System.out.println("What is your name?");
        String buyer = input.next();
        if (i.getBidCount() == 0) {
            System.out.println("Bid made by " + buyer + " for " + i.getCurrentPrice());
            i.incrementBidCount();
            i.setBuyer(buyer);
        } else if ((i.getCurrentPrice() + i.getBidIncrement()) >= i.getBuyOut()) {
            System.out.println("Item sold to " + buyer + " for " + i.getBuyOut());
            i.setBuyer(buyer);
            currentProfit += i.getBuyOut();
            auctioningList.removeItem(i.getName());
        } else {
            i.setCurrentPrice(i.getCurrentPrice() + i.getBidIncrement());
            System.out.println("Bid made by " + buyer + " for " + i.getCurrentPrice());
            i.setBuyer(buyer);
        }
    }

    // getters:

    // EFFECTS: returns current item auction house is holding
    public Item getCurrentItem() {
        return currentItem;
    }

    // setters:

    // EFFECTS: sets current item to item given, i.
    public void setCurrentItem(Item i) {
        currentItem = i;
    }
}
