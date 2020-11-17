package ui;

import model.AuctioningList;
import model.Item;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

    public AuctionHouseFrame(AuctioningList list) {
        setLayout(new FlowLayout());
        this.list = list;
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 125);
        setTitle("Auction House");
        initPanels();
        initButtons();

        currentItem = list.getFirstItem();
        nameLabel = new JLabel("Name: " + currentItem.getName());
        currentPriceLabel = new JLabel("Current Price: " + currentItem.getInitialPrice());
        bidIncrementLabel = new JLabel("Bid Increment: " + currentItem.getBidIncrement());
        buyOutLabel = new JLabel("Buy Out: " + currentItem.getBuyOut());
        itemPanel.add(nameLabel);
        itemPanel.add(currentPriceLabel);
        itemPanel.add(bidIncrementLabel);
        itemPanel.add(buyOutLabel);
    }

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

    private void initPanels() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        itemPanel = new JPanel();
        buttonsPanel = new JPanel();
        mainPanel.add(itemPanel);
        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
    }

    private void updateAuctionHouse() {
        if (list.getList().isEmpty()) {
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
                            + currentProfit,"Results", JOptionPane.INFORMATION_MESSAGE, dog);
            setVisible(false);
        } else {
            currentItem = list.getFirstItem();
            nameLabel.setText("Name: " + currentItem.getName());
            currentPriceLabel.setText("Current Price: " + currentItem.getInitialPrice());
            bidIncrementLabel.setText("Bid Increment: " + currentItem.getBidIncrement());
            buyOutLabel.setText("Buy Out: " + currentItem.getBuyOut());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("bid")) {
            doBidding();
        } else if (e.getActionCommand().equals("no")) {
            finishItem();
        }
    }

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

    public void finishItem() {
        if (currentItem.getBidCount() == 0) {
            JOptionPane.showMessageDialog(this, "Item had no bidders");
        } else {
            currentProfit += currentItem.getCurrentPrice();
            JOptionPane.showMessageDialog(this, "Item has been sold for: "
                    + currentItem.getCurrentPrice());
        }
        list.removeItem(currentItem.getName());
        updateAuctionHouse();
    }
}
