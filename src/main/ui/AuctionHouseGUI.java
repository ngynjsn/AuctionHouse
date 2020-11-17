package ui;

import model.AuctioningList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Main JFrame that will display the add, remove, view, save, load items features for the auctioning list. Also is the
// entry point to begin the auction house
public class AuctionHouseGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/auctioninglist.json";
    private AuctioningList auctioningList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel mainPanel;
    private JPanel addPanel;
    private JPanel removePanel;
    private JPanel viewPanel;
    private JPanel savePanel;
    private JPanel loadPanel;
    private JPanel runPanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton runButton;
    private ImageIcon checkMark = new ImageIcon("./data/checkmark.png");
    private ImageIcon error = new ImageIcon("./data/error.png");

    // MODIFIES: this
    // EFFECTS: creates the AuctionHouseGUI
    public AuctionHouseGUI() {
        super("Auction House");
        auctioningList = new AuctioningList("Jason");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initButtons();
        initJsonButtons();
        initPanels();
        storePanelsAndButtons();

        setLocationRelativeTo(null);
        add(mainPanel);
        pack();
        setResizable(false);
        setVisible(true);
        initJson();
    }

    // MODIFIES: this
    // EFFECTS: initializes the JSON reader and JSON writer to be able to save and load from file
    private void initJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes the add and remove buttons used for modifying the auctioning list
    //          inits. the view button to view items in the list
    //          inits. the run button used to run the auction house
    private void initButtons() {
        addButton = new JButton("Add an item");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        removeButton = new JButton("Remove an item");
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);
        viewButton = new JButton("View your items");
        viewButton.setActionCommand("view");
        viewButton.addActionListener(this);
        runButton = new JButton("Run auction house");
        runButton.setActionCommand("run");
        runButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the save and load buttons used for saving and loading files
    private void initJsonButtons() {
        saveButton = new JButton("Save your items");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        loadButton = new JButton("Load saved items");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes the panels that are separated depending on its purpose
    private void initPanels() {
        mainPanel = new JPanel();
        addPanel = new JPanel();
        removePanel = new JPanel();
        viewPanel = new JPanel();
        savePanel = new JPanel();
        loadPanel = new JPanel();
        runPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: stores the buttons into specific panels and the specific panels are all stored into a main panel.
    private void storePanelsAndButtons() {
        mainPanel.setLayout(new GridLayout(6, 1));
        addPanel.add(new JLabel("Add an item:"));
        addPanel.add(addButton);
        mainPanel.add(addPanel);
        removePanel.add(new JLabel("Remove an item:"));
        removePanel.add(removeButton);
        mainPanel.add(removePanel);
        viewPanel.add(new JLabel("View your items:"));
        viewPanel.add(viewButton);
        mainPanel.add(viewPanel);
        savePanel.add(new JLabel("Save your items:"));
        savePanel.add(saveButton);
        mainPanel.add(savePanel);
        loadPanel.add(new JLabel("Load saved items:"));
        loadPanel.add(loadButton);
        mainPanel.add(loadPanel);
        runPanel.add(new JLabel("Run auction house:"));
        runPanel.add(runButton);
        mainPanel.add(runPanel);
    }

    // MODIFIES: this
    // EFFECTS: processes commands based on which button is pressed.
    //          add button will continue with the process of adding an item.
    //          remove button will continue with the process of removing an item.
    //          view button will continue with the process of viewing the items in the auctioning list.
    //          load button will load items into an auctioning list from JSON file.
    //          save button will save items in an auctioning list to file in JSON.
    //          run button will continue with the process of running the auction house.
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addItem();
                break;
            case "remove":
                removeItem();
                break;
            case "view":
                viewItems();
                break;
            case "load":
                loadItems();
                break;
            case "save":
                saveItems();
                break;
            case "run":
                runHouse();
                break;
        }
    }

    // EFFECTS: creates a new frame where user can input information about an item. The item will be added
    //          into the auctioning list
    private void addItem() {
        new AddFrame(auctioningList);
    }

    // EFFECTS: creates a new frame where user can input the name of an item that they want to remove. The item will
    //          be removed from the auctioning list if it is found.
    private void removeItem() {
        new RemoveFrame(auctioningList);
    }

    // EFFECTS: creates a new frame where user can view their items in the auctioning list.
    private void viewItems() {
        new ViewFrame(auctioningList);
    }

    // EFFECTS: showcases error if button is pressed when no items have been placed into the auctioning list.
    //          if list is not empty, creates a new frame which begins the auction house.
    private void runHouse() {
        if (auctioningList.getList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items are in the list",
                    "Message", JOptionPane.INFORMATION_MESSAGE, error);
        } else {
            new AuctionHouseFrame(auctioningList);
        }
    }

    // EFFECTS: loads items into auctioning list from JSON file
    private void loadItems() {
        try {
            auctioningList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Items successfully loaded",
                    "Message", JOptionPane.INFORMATION_MESSAGE, checkMark);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Items failed to load",
                    "Message", JOptionPane.INFORMATION_MESSAGE, error);
        }
    }

    // EFFECTS: saves items from auctioning list into JSON file
    private void saveItems() {
        try {
            jsonWriter.open();
            jsonWriter.write(auctioningList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Items successfully saved",
                    "Message", JOptionPane.INFORMATION_MESSAGE, checkMark);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Items failed to save",
                    "Message", JOptionPane.INFORMATION_MESSAGE, error);
        }
    }

}
