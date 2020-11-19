package ui;

import exceptions.EmptyListException;
import model.AuctioningList;
import model.Item;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// JFrame that pops up when the user wants to run the auction house. User sees the item for sale
// and has options to bid or not
public class AuctionHouseFrame extends JFrame implements ActionListener {
    private AuctioningList list;
    private Item currentItem;
    private double currentProfit;
    private JPanel itemPanel;
    private JPanel buttonsPanel;
    private JLabel currentPriceLabel;
    private JLabel nameLabel;
    private JLabel bidIncrementLabel;
    private JLabel buyOutLabel;
    private ImageIcon checkmark = new ImageIcon("./data/checkmark.png");
    private ImageIcon error = new ImageIcon("./data/error.png");
    private ImageIcon dog = new ImageIcon("./data/tobs.jpg");

    // MODIFIES: this
    // EFFECTS: creates the AuctionHouseFrame and assigns this.list to list
    public AuctionHouseFrame(AuctioningList list) throws EmptyListException {
        currentItem = list.getFirstItem();

        setLayout(new FlowLayout());
        this.list = list;
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 125);
        setTitle("Auction House");
        initPanels();
        initButtons();

        nameLabel = new JLabel("Name: " + currentItem.getName());
        currentPriceLabel = new JLabel("Current Price: " + currentItem.getInitialPrice());
        bidIncrementLabel = new JLabel("Bid Increment: " + currentItem.getBidIncrement());
        buyOutLabel = new JLabel("Buy Out: " + currentItem.getBuyOut());
        itemPanel.add(nameLabel);
        itemPanel.add(currentPriceLabel);
        itemPanel.add(bidIncrementLabel);
        itemPanel.add(buyOutLabel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons: bid and no, that could be pressed by the user.
    private void initButtons() {
        JButton bidButton = new JButton("Bid");
        JButton noButton = new JButton("No");
        bidButton.setActionCommand("bid");
        noButton.setActionCommand("no");
        bidButton.addActionListener(this);
        noButton.addActionListener(this);
        buttonsPanel.add(bidButton);
        buttonsPanel.add(noButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes the panels that are visible to the user. Main panel is separated into the items panel,
    // which displays item info, and the buttons panel which holds the bid and no buttons
    private void initPanels() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        itemPanel = new JPanel();
        buttonsPanel = new JPanel();
        mainPanel.add(itemPanel);
        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: updates the current item's information to accurately represent the item's new current price.
    // If item has already been purchased, this instead updates the items panel to showcase next item.
    // If there are no more items to be auctioned, returns the total profit that the user has made along with sound.
    private void updateAuctionHouse() {
        try {
            currentItem = list.getFirstItem();
        } catch (EmptyListException e) {
            String soundName = "./data/Yay.wav";
            try {
                AudioInputStream audioInputStream
                        = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
                JOptionPane.showMessageDialog(this, "Audio failed to load",
                        "Message", JOptionPane.INFORMATION_MESSAGE, error);
            }
            JOptionPane.showMessageDialog(this, "Total profit is: "
                    + currentProfit, "Results", JOptionPane.INFORMATION_MESSAGE, dog);
            setVisible(false);
        }
        nameLabel.setText("Name: " + currentItem.getName());
        currentPriceLabel.setText("Current Price: " + currentItem.getInitialPrice());
        bidIncrementLabel.setText("Bid Increment: " + currentItem.getBidIncrement());
        buyOutLabel.setText("Buy Out: " + currentItem.getBuyOut());
    }



    // MODIFIES: this
    // EFFECTS: processes commands based on the button pressed. Pressing 'bid' will continue with the doBidding() method
    // where as pressing 'no' will continue with the finishItem() method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("bid")) {
            doBidding();
        } else if (e.getActionCommand().equals("no")) {
            finishItem();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the bidding functions. Begins bid with initial price and prompts bidder's name.
    //          Subsequent bids increments current item price by bid amount and remembers bidder's name.
    //          Once the current price of an item exceeds buy out price, current profit is incremented by
    //          item's current price, the item is removed from the auctioning list, a message is presented
    //          to display how much the item is sold for and who bought it, and auction house is updated for next item.
    public void doBidding() {
        String name = JOptionPane.showInputDialog(this, "Enter your name: ",
                "Bidder Info", JOptionPane.PLAIN_MESSAGE);
        if (name != null) {
            if (currentItem.getBidCount() == 0) {
                currentItem.incrementBidCount();
                currentItem.setBuyer(name);
            } else if (currentItem.getCurrentPrice() + currentItem.getBidIncrement() >= currentItem.getBuyOut()) {
                currentItem.setBuyer(name);
                currentProfit += currentItem.getBuyOut();
                list.removeItem(currentItem.getName());
                JOptionPane.showMessageDialog(this, currentItem.getName() + " has been sold to: "
                        + name + " for " + currentItem.getBuyOut());
                updateAuctionHouse();
            } else {
                currentItem.setCurrentPrice(currentItem.getCurrentPrice() + currentItem.getBidIncrement());
                currentItem.setBuyer(name);
                currentPriceLabel.setText("Current Price: " + currentItem.getCurrentPrice());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: continues the auction house process
    //          If item had no bidders, show message that the item has not been sold.
    //          Else adds currentItem's price to currentProfit and shows how much it has been sold for and to who.
    //          Updates auction house to move on to the next item.
    public void finishItem() {
        if (currentItem.getBidCount() == 0) {
            JOptionPane.showMessageDialog(this, "Item had no bidders");
        } else {
            currentProfit += currentItem.getCurrentPrice();
            JOptionPane.showMessageDialog(this, "Item has been sold to " + currentItem.getBuyer()
                    + " for: " + currentItem.getCurrentPrice());
        }
        list.removeItem(currentItem.getName());
        updateAuctionHouse();
    }
}
