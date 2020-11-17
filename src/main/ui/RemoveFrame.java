package ui;

import model.AuctioningList;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// JFrame that pops up when user is trying to remove an item from an auctioning list
public class RemoveFrame extends JFrame implements ActionListener {
    private JLabel itemName;
    private TextField tfItemName;
    private JButton removeItem;
    private AuctioningList list;
    private ImageIcon error = new ImageIcon("./data/error.png");

    // MODIFIES: this
    // EFFECTS: constructs the RemoveFrame and assigns this.list to list
    public RemoveFrame(AuctioningList list) {
        setLayout(new FlowLayout());
        this.list = list;
        itemName = new JLabel("Enter the name of the item: ");
        tfItemName = new TextField(4);
        removeItem = new JButton("Remove");
        removeItem.addActionListener(this);
        add(itemName);
        add(tfItemName);
        add(removeItem);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(250, 100);
        setResizable(false);
        setTitle("Item removal");
    }

    // MODIFIES: this
    // EFFECTS: Reads the name of an item that is given into the text field then checks if the item
    //          is in the auctioning list. If it is, then remove the item from the auctioning list and play a sound.
    //          else, throw a message to the user that the item with the given name is not found.
    @Override
    public void actionPerformed(ActionEvent e) {
        String itemName = tfItemName.getText();
        if (list.removeItem(itemName)) {
            String soundName = "./data/Ding.wav";
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                        new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
                JOptionPane.showMessageDialog(this, "Audio failed to load",
                        "Message", JOptionPane.INFORMATION_MESSAGE, error);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Item was not found");
        }
        setVisible(false);
    }
}
