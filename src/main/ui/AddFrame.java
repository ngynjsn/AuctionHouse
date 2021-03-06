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

// JFrame that pops up when user is trying to add an item into the auctioning list. Prompts for item name,
// initial price, bid increments, and buy out price.
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
    private ImageIcon error = new ImageIcon("./data/error.png");

    // MODIFIES: this
    // EFFECTS: constructs the AddFrame and assigns this.list to list.
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

    // MODIFIES: this
    // EFFECTS: initializes the labels that are accompanied with a text field
    private void initializeLabels() {
        itemName = new JLabel("Enter the name of your item: ");
        initialPrice = new JLabel("Enter your initial price: ");
        bidIncrement = new JLabel("Enter your bid increment: ");
        buyOut = new JLabel("Enter the item's buyout offer: ");
    }

    // MODIFIES: this
    // EFFECTS: initializes the text fields for user to input information about the item
    private void initializeTextFields() {
        tfItemName = new TextField(4);
        tfInitialPrice = new TextField(4);
        tfBidIncrement = new TextField(4);
        tfBuyOut = new TextField(4);
    }

    // MODIFIES: this
    // EFFECTS: adds labels and text fields onto frame
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

    // MODIFIES: this
    // EFFECTS: creates item based on information provided in text field then adds the item into the auctioning list.
    // also plays a ding sound on completion.
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
            JOptionPane.showMessageDialog(this, "Audio failed to load",
                    "Message", JOptionPane.INFORMATION_MESSAGE, error);
        }
        setVisible(false);
    }



}
