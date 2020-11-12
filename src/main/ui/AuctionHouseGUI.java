package ui;

import model.AuctioningList;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class AuctionHouseGUI extends JFrame implements ActionListener {
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
        initJsonButtons();
        initPanels();
        storePanels();

        add(mainPanel);
        pack();
        setVisible(true);
        initJson();
    }

    private void initJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

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

    private void initJsonButtons() {
        saveButton = new JButton("Save your items");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        loadButton = new JButton("Load previously saved items");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
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
        loadPanel.add(new JLabel("Load previously saved items:"));
        loadPanel.add(loadButton);
        mainPanel.add(loadPanel);
        runPanel.add(new JLabel("Run auction house:"));
        runPanel.add(runButton);
        mainPanel.add(runPanel);
    }

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
            case "run":
                runHouse();
                break;
            case "load":
                loadItems();
                break;
            case "save":
                saveItems();
                break;
        }
    }

    private void addItem() {
        System.out.println("hi");
    }

    private void removeItem() {
        System.out.println("bye");
    }

    private void viewItems() {
        System.out.println("a");
    }

    private void runHouse() {
        System.out.println("b");
    }

    private void loadItems() {
        System.out.println("c");
    }

    private void saveItems() {
        System.out.println("d");
    }

}