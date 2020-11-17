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
        loadButton = new JButton("Load saved items");
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
        new AddFrame(auctioningList);
    }

    private void removeItem() {
        new RemoveFrame(auctioningList);
    }

    private void viewItems() {
        new ViewFrame(auctioningList);
    }

    private void runHouse() {
        if (auctioningList.getList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items are in the list",
                    "Message", JOptionPane.INFORMATION_MESSAGE, error);
        } else {
            new AuctionHouseFrame(auctioningList);
        }
    }

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
