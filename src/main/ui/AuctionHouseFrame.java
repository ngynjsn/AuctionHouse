package ui;

import model.AuctioningList;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuctionHouseFrame extends JFrame implements ActionListener {
    private AuctioningList list;
    private Item currentItem;
    private double currentProfit;
    private JPanel mainPanel;
    private JPanel itemPanel;
    private JPanel buttonsPanel;
    private JButton bidButton;
    private JButton noButton;
    private ImageIcon checkmark = new ImageIcon("./data/checkmark.png");

    public AuctionHouseFrame(AuctioningList list) {
        setLayout(new FlowLayout());
        this.list = list;
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 200);
        setTitle("Auction House");
        runAuctionHouse();
    }

    private void initButtons() {
        bidButton = new JButton("Bid");
        noButton = new JButton("No");
        bidButton.setActionCommand("bid");
        noButton.setActionCommand("no");
        buttonsPanel.add(bidButton);
        buttonsPanel.add(noButton);
    }

    private void initPanels() {
        mainPanel = new JPanel(new GridLayout(2, 1));
        itemPanel = new JPanel();
        buttonsPanel = new JPanel();
        mainPanel.add(itemPanel);
        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
    }

    private void runAuctionHouse() {
        initPanels();
        initButtons();

//        while (keepGoing) {
//            if (list.getList().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Total profit is: " + currentProfit);
//                keepGoing = false;
//            } else {
//                currentItem = list.getFirstItem();
//                JLabel nameLabel = new JLabel("Name: " + currentItem.getName());
//                JLabel initialPriceLabel = new JLabel("Initial Price: " + currentItem.getInitialPrice());
//                JLabel bidIncrementLabel = new JLabel("Bid Increment: " + currentItem.getBidIncrement());
//                JLabel buyOutLabel = new JLabel("Buy Out: " + currentItem.getBuyOut());
//                itemPanel.add(nameLabel);
//                itemPanel.add(initialPriceLabel);
//                itemPanel.add(bidIncrementLabel);
//                itemPanel.add(buyOutLabel);
//            }
//        }
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
        System.out.println("hi");
        String name = JOptionPane.showInputDialog(this, "Enter your name: ",
                "Bidder Info", JOptionPane.PLAIN_MESSAGE);
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
    }
}
