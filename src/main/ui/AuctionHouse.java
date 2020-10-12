package ui;


import model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// THE APP TO BEGIN THE AUCTION HOUSE

public class AuctionHouse {
    private List<Item> auctioningList;
    private String seller;
    private Item currentItem;
    private double currentProfit = 0;
    private Scanner input;

    // EFFECTS: constructor to run the auction house
    public AuctionHouse() {
        runAuctionHouse();
    }

    // EFFECTS: constructor to help with tests
    //          assigns the seller's name to n and creates empty auctioning list
    public AuctionHouse(String n) {
        auctioningList = new ArrayList<Item>();
        seller = n;
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runAuctionHouse() {
        boolean keepGoing = true;
        String command = null;

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
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add item");
        System.out.println("\tr -> remove item");
        System.out.println("\tv -> view items");
        System.out.println("\tb -> begin auction house");
    }

    // MODIFIES: this
    // EFFECTS: initializes empty list
    private void init() {
        auctioningList = new ArrayList<Item>();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
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

    // REQUIRES: auctioning list is non-empty
    // MODIFIES: this
    // EFFECTS: prompts for the sellers name and then begins the auction house
    private void doAuctionHouse() {
        System.out.println("What is your name?");
        seller = input.next();
        System.out.println("Let's get it started, " + seller + "!");
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            if (auctioningList.isEmpty()) {
                System.out.println("All items have had a chance to be auctioned!");
                System.out.println("Total profit is: " + currentProfit);
                keepGoing = false;
            } else {
                currentItem = getFirstItem();
                System.out.println("First item for sale is: " + currentItem.getName());
                System.out.println("Initial price is " + currentItem.getInitialPrice() + ". Bidding increments are "
                        + currentItem.getBidIncrement() + ". Buyout price is " + currentItem.getBuyOut());
                System.out.println("Would you like to bid for this item?");
                command = input.next();
                command = command.toLowerCase();
                processCommandWithItem(command, currentItem);
            }
        }
    }

    // MODIFIES: this, item
    // EFFECTS: process commands that are given by user
    private void processCommandWithItem(String command, Item i) {

    }


    // MODIFIES:
    private void doBidding() {

    }

    // MODIFIES: this
    // EFFECTS: begins the removal of an item, if found. return true statement
    //          if item is found, else return false statement
    private void doRemoveItem() {
        System.out.println("What is the name of the item you are trying to remove?");
        String name = input.next();
        if (removeItem(name)) {
            System.out.println("The item has been removed.");
        } else {
            System.out.println("Sorry, cannot find item with given name.");
        }
    }

    // MODIFIES: this
    // EFFECTS: begins the addition of a newly created item.
    private void doAddItem() {
        Item item = createItem();
        addItem(item);
    }

    // EFFECTS: shows the user the list of item in auctioning list
    private void doViewList() {
        int i = 1;
        for (Item next : auctioningList) {
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
        Double initPrice = input.nextDouble();
        System.out.println("What would you like to be the bid increment?");
        Double bidPrice = input.nextDouble();
        System.out.println("What would you like to be the buyout offer?");
        Double buyOut = input.nextDouble();

        Item item = new Item(name, initPrice, bidPrice, buyOut);

        return item;
    }

    // MODIFIES: this
    // EFFECTS: adds an item, i, into the auctioning list
    public void addItem(Item i) {
        auctioningList.add(i);
    }

    // REQUIRES: item with name, n, needs to be already in the list
    // MODIFIES: this
    // EFFECTS: removes an item with the given name n
    public boolean removeItem(String n) {
        for (Item next : auctioningList) {
            if (next.getName().equals(n)) {
                auctioningList.remove(next);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: auctioningList is non empty
    // EFFECTS: returns the first item on auctioningList
    public Item getFirstItem() {
        return auctioningList.get(0);
    }


    // getters:

    public List<Item> viewItems() {
        return auctioningList;
    }

    public String getSeller() {
        return seller;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    // setters:

    public void setSeller(String n) {
        seller = n;
    }

    public void setCurrentItem(Item i) {
        currentItem = i;
    }
}
