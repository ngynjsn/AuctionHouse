package ui;

import model.AuctioningList;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class AuctionHouseGUI extends JFrame {
    private static final String JSON_STORE = "./data/auctioninglist.json";
    private AuctioningList auctioningList;
    private Item currentItem;
    private double currentProfit = 0;
    private Scanner input;
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

    public AuctionHouseGUI() {
        super("Auction House");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initButtons();
        initPanels();
        storePanels();

        add(mainPanel);
        pack();
        setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void initButtons() {
        addButton = new JButton("Add an item");
        removeButton = new JButton("Remove an item");
        viewButton = new JButton("View your items");
        saveButton = new JButton("Save your items");
        loadButton = new JButton("Load previously saved items");
        runButton = new JButton("Run auction house");
    }

    private void initPanels() {
        mainPanel = new JPanel();
        addPanel = new JPanel();
        removePanel = new JPanel();
        viewPanel = new JPanel();
        savePanel = new JPanel();
        loadPanel = new JPanel();
        runPanel = new JPanel();
    }

    private void storePanels() {
        mainPanel.setLayout(new GridLayout(6,1));
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
        loadPanel.add(new JLabel("Load previously saved items:"));
        loadPanel.add(loadButton);
        mainPanel.add(loadPanel);
        runPanel.add(new JLabel("Run auction house:"));
        runPanel.add(runButton);
        mainPanel.add(runPanel);
    }


}
