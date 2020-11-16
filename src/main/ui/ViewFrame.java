package ui;

import model.AuctioningList;
import model.Item;

import javax.swing.*;
import java.awt.*;

public class ViewFrame extends JFrame {
    private AuctioningList list;
    private JPanel mainPanel;

    public ViewFrame(AuctioningList list) {
        setSize(new Dimension(150, 50 + list.getList().size() * 100));
        this.list = list;
        mainPanel = new JPanel(new GridLayout(list.getList().size(), 1));
        produceItems();
        add(mainPanel);
        setLocationRelativeTo(null);
        setTitle("Items");
        setResizable(false);
        setVisible(true);
    }

    private void produceItems() {
        if (list.getList().size() == 0) {
            setSize(200, 50);
            JLabel noItems = new JLabel("No items have been added");
            mainPanel.add(noItems);
        } else {
            for (Item i : list.getList()) {
                JLabel nameLabel = new JLabel("Name: " + i.getName());
                JLabel initialPriceLabel = new JLabel("Initial Price: " + i.getInitialPrice());
                JLabel bidIncrementLabel = new JLabel("Bid Increment: " + i.getBidIncrement());
                JLabel buyOutLabel = new JLabel("Buy Out: " + i.getBuyOut());
                JPanel itemPanel = new JPanel();
                itemPanel.add(nameLabel);
                itemPanel.add(initialPriceLabel);
                itemPanel.add(bidIncrementLabel);
                itemPanel.add(buyOutLabel);
                mainPanel.add(itemPanel);
            }
        }
    }

}