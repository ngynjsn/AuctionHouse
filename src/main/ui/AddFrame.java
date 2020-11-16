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

public class AddFrame extends JFrame implements ActionListener {
    private JLabel itemName;
    private JLabel initialPrice;
    private JLabel bidIncrement;
    private JLabel buyOut;
    private TextField tfItemName;
    private TextField tfInitialPrice;
    private TextField tfBidIncrement;
    private TextField tfBuyOut;
    private JButton addItem;
    private AuctioningList list;

    public AddFrame(AuctioningList list) {
        this.list = list;

        setLayout(new FlowLayout());

        initializeLabels();
        initializeTextFields();
        addLabelsAndTextFields();
        addItem = new JButton("Add");
        add(addItem);
        addItem.addActionListener(this);

        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Adding an item");
        setSize(275,200);
        setVisible(true);
    }

    private void initializeLabels() {
        itemName = new JLabel("Enter the name of your item: ");
        initialPrice = new JLabel("Enter your initial price: ");
        bidIncrement = new JLabel("Enter your bid increment: ");
        buyOut = new JLabel("Enter the item's buyout offer: ");
    }

    private void initializeTextFields() {
        tfItemName = new TextField(3);
        tfInitialPrice = new TextField(3);
        tfBidIncrement = new TextField(3);
        tfBuyOut = new TextField(3);
    }

    private void addLabelsAndTextFields() {
        add(itemName);
        add(tfItemName);
        add(initialPrice);
        add(tfInitialPrice);
        add(bidIncrement);
        add(tfBidIncrement);
        add(buyOut);
        add(tfBuyOut);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = tfItemName.getText();
        double price = Double.parseDouble(tfInitialPrice.getText());
        double bid = Double.parseDouble(tfBidIncrement.getText());
        double buyOut = Double.parseDouble(tfBuyOut.getText());
        Item itemMade = new Item(name, price, bid, buyOut);
        list.addItem(itemMade);
        String soundName = "./data/Ding.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            exception.printStackTrace();
        }
        setVisible(false);
    }



}
