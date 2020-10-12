package ui;


import model.Item;

import java.util.List;
import java.util.Scanner;

// THE APP TO BEGIN THE AUCTION HOUSE

public class AuctionHouse {
    private List<Item> auctioningList;
    private String seller;
    private Item currentItem;
    private Scanner input;

    public AuctionHouse() {
        runAuctionHouse();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runAuctionHouse() {
        boolean keepGoing = true;
        String command = null;

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
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("r")) {
            doRemoveItem();
        }
    }

    // MODIFIES: this
    // EFFECTS: begins the removal of an item, if found. return true statement
    //          if item is found, else return false statement
    private void doRemoveItem() {
        System.out.println("What is the name of the item you are trying to remove?");
        String name = input.next();
        if (removeItem(name)) {
            System.out.println("The item has been removed.");
        }
        System.out.println("Sorry, cannot find item with given name.");
    }

    // MODIFIES: this
    // EFFECTS: begins the addition of a newly created item.
    private void doAddItem() {
        Item item = createItem();
        addItem(item);
    }

    // EFFECTS: creates an item based on prompts given to user
    private Item createItem() {
        System.out.println("What is the name of your item?");
        String name = input.next();
        System.out.println("What do you want to be the initial price?");
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

    // REQUIRES: auctiongList is non empty
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

    // setters:

    public void setSeller(String n) {
        seller = n;
    }

    public void setCurrentItem(Item i) {
        currentItem = i;
    }
}
